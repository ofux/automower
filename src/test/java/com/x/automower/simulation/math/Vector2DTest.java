package com.x.automower.simulation.math;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Vector2DTest {

    @Test
    void add() {
        // Arrange
        var v1 = new Vector2D(2, 3);
        var v2 = new Vector2D(-1, 1);

        // Act
        var result = v1.add(v2);

        // Assert
        assertThat(result).isEqualTo(new Vector2D(1, 4));
    }

    @Test
    void addZero() {
        // Arrange
        var v1 = new Vector2D(2, 3);
        var v2 = new Vector2D(0, 0);

        // Act
        var result = v1.add(v2);

        // Assert
        assertThat(result).isEqualTo(new Vector2D(2, 3));
    }
}