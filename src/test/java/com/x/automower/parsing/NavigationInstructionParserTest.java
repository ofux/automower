package com.x.automower.parsing;

import com.x.automower.simulation.navigation.MoveForwardAction;
import com.x.automower.simulation.navigation.TurnLeftAction;
import com.x.automower.simulation.navigation.TurnRightAction;
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
            assertThat(instruction).isInstanceOf(MoveForwardAction.class);
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
            assertThat(instruction).isInstanceOf(TurnLeftAction.class);
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
            assertThat(instruction).isInstanceOf(TurnRightAction.class);
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
        var instruction = new MoveForwardAction();

        // Act
        var output = parser.serialize(instruction);

        // Assert
        assertThat(output).isEqualTo("F");
    }

    @Test
    void serializeLeft() {
        // Arrange
        var instruction = new TurnLeftAction();

        // Act
        var output = parser.serialize(instruction);

        // Assert
        assertThat(output).isEqualTo("L");
    }

    @Test
    void serializeRight() {
        // Arrange
        var instruction = new TurnRightAction();

        // Act
        var output = parser.serialize(instruction);

        // Assert
        assertThat(output).isEqualTo("R");
    }
}