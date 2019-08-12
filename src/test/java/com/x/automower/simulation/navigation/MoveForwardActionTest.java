package com.x.automower.simulation.navigation;

import com.x.automower.simulation.math.Orientation;
import com.x.automower.simulation.math.Transform;
import com.x.automower.simulation.math.Vector2D;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.x.automower.simulation.navigation.NavMeshUtils.createSimpleNavMesh;

class MoveForwardActionTest {

    @Test
    void applyToNorth() {
        // Arrange
        var action = new MoveForwardAction();
        var navMesh = createSimpleNavMesh();

        // Act
        var result = action.apply(navMesh, new Transform(new Vector2D(0, 0), Orientation.NORTH));

        // Assert
        Assertions.assertThat(result).isEqualTo(new Transform(new Vector2D(0, 1), Orientation.NORTH));
    }

    @Test
    void applyToEast() {
        // Arrange
        var action = new MoveForwardAction();
        var navMesh = createSimpleNavMesh();

        // Act
        var result = action.apply(navMesh, new Transform(new Vector2D(3, 3), Orientation.EAST));

        // Assert
        Assertions.assertThat(result).isEqualTo(new Transform(new Vector2D(4, 3), Orientation.EAST));
    }

    @Test
    void applyToSouth() {
        // Arrange
        var action = new MoveForwardAction();
        var navMesh = createSimpleNavMesh();

        // Act
        var result = action.apply(navMesh, new Transform(new Vector2D(0, 4), Orientation.SOUTH));

        // Assert
        Assertions.assertThat(result).isEqualTo(new Transform(new Vector2D(0, 3), Orientation.SOUTH));
    }

    @Test
    void applyToWest() {
        // Arrange
        var action = new MoveForwardAction();
        var navMesh = createSimpleNavMesh();

        // Act
        var result = action.apply(navMesh, new Transform(new Vector2D(3, 1), Orientation.WEST));

        // Assert
        Assertions.assertThat(result).isEqualTo(new Transform(new Vector2D(2, 1), Orientation.WEST));
    }

    @Test
    void applyNotNavigable() {
        // Arrange
        var action = new MoveForwardAction();
        var navMesh = createSimpleNavMesh();

        // Act
        var result = action.apply(navMesh, new Transform(new Vector2D(2, 0), Orientation.SOUTH));

        // Assert
        Assertions.assertThat(result).isEqualTo(new Transform(new Vector2D(2, 0), Orientation.SOUTH));
    }


}