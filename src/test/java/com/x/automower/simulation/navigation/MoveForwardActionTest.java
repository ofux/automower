package com.x.automower.simulation.navigation;

import com.x.automower.simulation.math.Orientation;
import com.x.automower.simulation.math.Transform;
import com.x.automower.simulation.math.Vector2D;
import org.junit.jupiter.api.Test;

import static com.x.automower.simulation.navigation.NavMeshUtils.createSimpleNavMesh;
import static org.assertj.core.api.Assertions.assertThat;

class MoveForwardActionTest {

    @Test
    void applyToNorth() {
        // Arrange
        var action = new MoveForwardAction();
        var navMesh = createSimpleNavMesh();

        // Act
        var result = action.apply(navMesh, new Transform(new Vector2D(0, 0), Orientation.NORTH));

        // Assert
        assertThat(result).isEqualTo(new Transform(new Vector2D(0, 1), Orientation.NORTH));
        assertThat(navMesh.isNavigable(new Vector2D(0, 1))).isFalse();
    }

    @Test
    void applyToEast() {
        // Arrange
        var action = new MoveForwardAction();
        var navMesh = createSimpleNavMesh();

        // Act
        var result = action.apply(navMesh, new Transform(new Vector2D(3, 3), Orientation.EAST));

        // Assert
        assertThat(result).isEqualTo(new Transform(new Vector2D(4, 3), Orientation.EAST));
        assertThat(navMesh.isNavigable(new Vector2D(4, 3))).isFalse();
    }

    @Test
    void applyToSouth() {
        // Arrange
        var action = new MoveForwardAction();
        var navMesh = createSimpleNavMesh();

        // Act
        var result = action.apply(navMesh, new Transform(new Vector2D(0, 4), Orientation.SOUTH));

        // Assert
        assertThat(result).isEqualTo(new Transform(new Vector2D(0, 3), Orientation.SOUTH));
        assertThat(navMesh.isNavigable(new Vector2D(0, 3))).isFalse();
    }

    @Test
    void applyToWest() {
        // Arrange
        var action = new MoveForwardAction();
        var navMesh = createSimpleNavMesh();

        // Act
        var result = action.apply(navMesh, new Transform(new Vector2D(3, 1), Orientation.WEST));

        // Assert
        assertThat(result).isEqualTo(new Transform(new Vector2D(2, 1), Orientation.WEST));
        assertThat(navMesh.isNavigable(new Vector2D(2, 1))).isFalse();
    }

    @Test
    void applyNotNavigable() {
        // Arrange
        var action = new MoveForwardAction();
        var navMesh = createSimpleNavMesh();

        // Act
        var result = action.apply(navMesh, new Transform(new Vector2D(2, 0), Orientation.SOUTH));

        // Assert
        assertThat(result).isEqualTo(new Transform(new Vector2D(2, 0), Orientation.SOUTH));
    }


}