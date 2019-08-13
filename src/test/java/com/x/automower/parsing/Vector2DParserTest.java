package com.x.automower.parsing;

import com.x.automower.simulation.math.Vector2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class Vector2DParserTest {

    private Vector2DParser parser;

    @BeforeEach
    void setUp() {
        parser = new Vector2DParser();
    }

    @Test
    void deserializeSimpleVector() {
        // Arrange
        var input = "3 8";

        assertThatCode(() -> {
            // Act
            var vector2D = parser.deserialize(input);

            // Assert
            assertThat(vector2D).isEqualTo(new Vector2D(3, 8));
        }).doesNotThrowAnyException();
    }

    @Test
    void deserializeVectorWithMultipleFigures() {
        // Arrange
        var input = "34 85";

        assertThatCode(() -> {
            // Act
            var vector2D = parser.deserialize(input);

            // Assert
            assertThat(vector2D).isEqualTo(new Vector2D(34, 85));
        }).doesNotThrowAnyException();
    }

    @Test
    void deserializeBadInputWithOnlyOneNumber() {
        // Arrange
        var input = "38";

        assertThatThrownBy(() -> {
            // Act
            parser.deserialize(input);
        })
                // Assert
                .isInstanceOf(ParsingException.class)
                .hasMessageContaining("38")
                .hasMessageContaining("Vector2D");
    }

    @Test
    void deserializeBadInputWithOnlyOneNonEmptyNumber() {
        // Arrange
        var input = "  38";

        assertThatThrownBy(() -> {
            // Act
            parser.deserialize(input);
        })
                // Assert
                .isInstanceOf(ParsingException.class)
                .hasMessageContaining("  38")
                .hasMessageContaining("Vector2D");
    }

    @Test
    void deserializeBadInputWithBadNumbers() {
        // Arrange
        var input = "one two";

        assertThatThrownBy(() -> {
            // Act
            parser.deserialize(input);
        })
                // Assert
                .isInstanceOf(ParsingException.class)
                .hasMessageContaining("one two")
                .hasMessageContaining("Vector2D");
    }

    @Test
    void serialize() {
        // Arrange
        var vector2D = new Vector2D(35, 8);

        // Act
        var output = parser.serialize(vector2D);

        // Assert
        assertThat(output).isEqualTo("35 8");
    }
}