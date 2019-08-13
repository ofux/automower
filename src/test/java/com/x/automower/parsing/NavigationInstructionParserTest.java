package com.x.automower.parsing;

import com.x.automower.simulation.navigation.MoveForwardInstruction;
import com.x.automower.simulation.navigation.TurnLeftInstruction;
import com.x.automower.simulation.navigation.TurnRightInstruction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NavigationInstructionParserTest {

    private NavigationInstructionParser parser;

    @BeforeEach
    void setUp() {
        parser = new NavigationInstructionParser();
    }

    @Test
    void deserializeForward() {
        // Arrange
        var input = "F";

        assertThatCode(() -> {
            // Act
            var instruction = parser.deserialize(input);

            // Assert
            assertThat(instruction).isInstanceOf(MoveForwardInstruction.class);
        }).doesNotThrowAnyException();
    }

    @Test
    void deserializeLeft() {
        // Arrange
        var input = "L";

        assertThatCode(() -> {
            // Act
            var instruction = parser.deserialize(input);

            // Assert
            assertThat(instruction).isInstanceOf(TurnLeftInstruction.class);
        }).doesNotThrowAnyException();
    }

    @Test
    void deserializeRight() {
        // Arrange
        var input = "R";

        assertThatCode(() -> {
            // Act
            var instruction = parser.deserialize(input);

            // Assert
            assertThat(instruction).isInstanceOf(TurnRightInstruction.class);
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
                .hasMessageContaining("NavigationInstruction");
    }

    @Test
    void serializeForward() {
        // Arrange
        var instruction = new MoveForwardInstruction();

        // Act
        var output = parser.serialize(instruction);

        // Assert
        assertThat(output).isEqualTo("F");
    }

    @Test
    void serializeLeft() {
        // Arrange
        var instruction = new TurnLeftInstruction();

        // Act
        var output = parser.serialize(instruction);

        // Assert
        assertThat(output).isEqualTo("L");
    }

    @Test
    void serializeRight() {
        // Arrange
        var instruction = new TurnRightInstruction();

        // Act
        var output = parser.serialize(instruction);

        // Assert
        assertThat(output).isEqualTo("R");
    }
}