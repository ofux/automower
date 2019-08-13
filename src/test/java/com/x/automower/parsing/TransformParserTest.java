package com.x.automower.parsing;

import com.x.automower.simulation.math.Orientation;
import com.x.automower.simulation.math.Transform;
import com.x.automower.simulation.math.Vector2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TransformParserTest {

    private TransformParser parser;

    @BeforeEach
    void setUp() {
        parser = new TransformParser();
    }

    @Test
    void deserialize() {
        // Arrange
        var input = "3 8 N";

        assertThatCode(() -> {
            // Act
            var transform = parser.deserialize(input);

            // Assert
            assertThat(transform).isEqualTo(new Transform(new Vector2D(3, 8), Orientation.NORTH));
        }).doesNotThrowAnyException();
    }

    @Test
    void deserializeWithDifferentInput() {
        // Arrange
        var input = "0 86 W";

        assertThatCode(() -> {
            // Act
            var transform = parser.deserialize(input);

            // Assert
            assertThat(transform).isEqualTo(new Transform(new Vector2D(0, 86), Orientation.WEST));
        }).doesNotThrowAnyException();
    }

    @Test
    void deserializeWithBadInput() {
        // Arrange
        var input = " 0 W";

        assertThatThrownBy(() -> {
            // Act
            parser.deserialize(input);
        })
                // Assert
                .isInstanceOf(ParsingException.class)
                .hasMessageContaining("0 W")
                .hasMessageContaining("Transform");
    }

    @Test
    void serialize() {
        // Arrange
        var transform = new Transform(new Vector2D(0, 86), Orientation.WEST);

        // Act
        var output = parser.serialize(transform);

        // Assert
        assertThat(output).isEqualTo("0 86 W");
    }
}