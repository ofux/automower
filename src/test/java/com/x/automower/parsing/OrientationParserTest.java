package com.x.automower.parsing;

import com.x.automower.simulation.math.Orientation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrientationParserTest {

    private OrientationParser parser;

    @BeforeEach
    void setUp() {
        parser = new OrientationParser();
    }

    @Test
    void deserializeNorth() {
        // Arrange
        var input = "N";

        // Act
        assertThatCode(() -> {
            // Act
            var orientation = parser.deserialize(input);

            // Assert
            assertThat(orientation).isEqualTo(Orientation.NORTH);
        }).doesNotThrowAnyException();
    }

    @Test
    void deserializeEast() {
        // Arrange
        var input = "E";

        // Act
        assertThatCode(() -> {
            // Act
            var orientation = parser.deserialize(input);

            // Assert
            assertThat(orientation).isEqualTo(Orientation.EAST);
        }).doesNotThrowAnyException();
    }

    @Test
    void deserializeWest() {
        // Arrange
        var input = "W";

        // Act
        assertThatCode(() -> {
            // Act
            var orientation = parser.deserialize(input);

            // Assert
            assertThat(orientation).isEqualTo(Orientation.WEST);
        }).doesNotThrowAnyException();
    }

    @Test
    void deserializeSouth() {
        // Arrange
        var input = "S";

        // Act
        assertThatCode(() -> {
            // Act
            var orientation = parser.deserialize(input);

            // Assert
            assertThat(orientation).isEqualTo(Orientation.SOUTH);
        }).doesNotThrowAnyException();
    }

    @Test
    void deserializeBadInput() {
        // Arrange
        var input = "K";

        assertThatThrownBy(() -> {
            // Act
            parser.deserialize(input);
        })
                // Assert
                .isInstanceOf(ParsingException.class)
                .hasMessageContaining("K")
                .hasMessageContaining("Orientation");
    }

    @Test
    void serializeNorth() {
        // Arrange
        var orientation = Orientation.NORTH;

        // Act
        var output = parser.serialize(orientation);

        // Assert
        assertThat(output).isEqualTo("N");
    }

    @Test
    void serializeEast() {
        // Arrange
        var orientation = Orientation.EAST;

        // Act
        var output = parser.serialize(orientation);

        // Assert
        assertThat(output).isEqualTo("E");
    }

    @Test
    void serializeWest() {
        // Arrange
        var orientation = Orientation.WEST;

        // Act
        var output = parser.serialize(orientation);

        // Assert
        assertThat(output).isEqualTo("W");
    }

    @Test
    void serializeSouth() {
        // Arrange
        var orientation = Orientation.SOUTH;

        // Act
        var output = parser.serialize(orientation);

        // Assert
        assertThat(output).isEqualTo("S");
    }
}