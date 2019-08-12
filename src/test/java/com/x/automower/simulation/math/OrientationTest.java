package com.x.automower.simulation.math;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrientationTest {

    @Test
    void getForwardPositionNorth() {
        // Arrange
        var o = Orientation.NORTH;

        // Act
        var p = o.getForwardPosition(new Vector2D(2, 3));

        // Assert
        assertThat(p).isEqualTo(new Vector2D(2, 4));
    }

    @Test
    void getForwardPositionEast() {
        // Arrange
        var o = Orientation.EAST;

        // Act
        var p = o.getForwardPosition(new Vector2D(2, 3));

        // Assert
        assertThat(p).isEqualTo(new Vector2D(3, 3));
    }

    @Test
    void getForwardPositionSouth() {
        // Arrange
        var o = Orientation.SOUTH;

        // Act
        var p = o.getForwardPosition(new Vector2D(2, 3));

        // Assert
        assertThat(p).isEqualTo(new Vector2D(2, 2));
    }

    @Test
    void getForwardPositionWest() {
        // Arrange
        var o = Orientation.WEST;

        // Act
        var p = o.getForwardPosition(new Vector2D(2, 3));

        // Assert
        assertThat(p).isEqualTo(new Vector2D(1, 3));
    }

    @Test
    void turnLeftFromNorth() {
        // Arrange
        var o = Orientation.NORTH;

        // Act
        var result = o.turnLeft();

        // Assert
        assertThat(result).isEqualTo(Orientation.WEST);
    }

    @Test
    void turnLeftFromEast() {
        // Arrange
        var o = Orientation.EAST;

        // Act
        var result = o.turnLeft();

        // Assert
        assertThat(result).isEqualTo(Orientation.NORTH);
    }

    @Test
    void turnLeftFromSouth() {
        // Arrange
        var o = Orientation.SOUTH;

        // Act
        var result = o.turnLeft();

        // Assert
        assertThat(result).isEqualTo(Orientation.EAST);
    }

    @Test
    void turnLeftFromWest() {
        // Arrange
        var o = Orientation.WEST;

        // Act
        var result = o.turnLeft();

        // Assert
        assertThat(result).isEqualTo(Orientation.SOUTH);
    }

    @Test
    void turnRightFromNorth() {
        // Arrange
        var o = Orientation.NORTH;

        // Act
        var result = o.turnRight();

        // Assert
        assertThat(result).isEqualTo(Orientation.EAST);
    }

    @Test
    void turnRightFromEast() {
        // Arrange
        var o = Orientation.EAST;

        // Act
        var result = o.turnRight();

        // Assert
        assertThat(result).isEqualTo(Orientation.SOUTH);
    }

    @Test
    void turnRightFromSouth() {
        // Arrange
        var o = Orientation.SOUTH;

        // Act
        var result = o.turnRight();

        // Assert
        assertThat(result).isEqualTo(Orientation.WEST);
    }

    @Test
    void turnRightFromWest() {
        // Arrange
        var o = Orientation.WEST;

        // Act
        var result = o.turnRight();

        // Assert
        assertThat(result).isEqualTo(Orientation.NORTH);
    }
}