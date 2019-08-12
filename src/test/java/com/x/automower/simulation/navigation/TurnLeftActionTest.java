package com.x.automower.simulation.navigation;

import com.x.automower.simulation.math.Orientation;
import com.x.automower.simulation.math.Transform;
import com.x.automower.simulation.math.Vector2D;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.x.automower.simulation.navigation.NavMeshUtils.createSimpleNavMesh;

class TurnLeftActionTest {

    @Test
    void apply() {
        // Arrange
        var action = new TurnLeftAction();
        var navMesh = createSimpleNavMesh();

        // Act
        var result = action.apply(navMesh, new Transform(new Vector2D(0, 0), Orientation.NORTH));

        // Assert
        Assertions.assertThat(result).isEqualTo(new Transform(new Vector2D(0, 0), Orientation.WEST));
    }
}