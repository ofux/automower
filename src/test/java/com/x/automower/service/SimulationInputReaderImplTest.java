package com.x.automower.service;

import com.x.automower.parsing.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimulationInputReaderImplTest {

    private SimulationInputReaderImpl reader;

    @BeforeEach
    void setUp() {
        var renderer = new ConsoleMowerRenderer(new TransformParser(new Vector2DParser(), new OrientationParser()));
        reader = new SimulationInputReaderImpl(
                new Vector2DParser(),
                new TransformParser(new Vector2DParser(), new OrientationParser()),
                new NavigationInstructionParser(),
                renderer);
    }

    @Test
    void readValidInput() {
        // Arrange
        var input = new ByteArrayInputStream(("5 5\n" +
                "1 2 N\n" +
                "LFLFLFLFF\n" +
                "3 3 E\n" +
                "FFRFFRFRRF").getBytes());

        assertThatCode(() -> {
            // Act
            var simulation = reader.read(input);

            // Assert
            Assertions.assertThat(simulation).isNotNull();
        }).doesNotThrowAnyException();
    }

    @Test
    void readValidInputWithEmptyInstructionSet() {
        // Arrange
        var input = new ByteArrayInputStream(("5 5\n" +
                "1 2 N\n" +
                "\n" +
                "3 3 E\n" +
                "FFRFFRFRRF").getBytes());

        assertThatCode(() -> {
            // Act
            var simulation = reader.read(input);

            // Assert
            Assertions.assertThat(simulation).isNotNull();
        }).doesNotThrowAnyException();
    }

    @Test
    void readValidInputWithEmptyLineAtEnd() {
        // Arrange
        var input = new ByteArrayInputStream(("5 5\n" +
                "1 2 N\n" +
                "LFLFLFLFF\n").getBytes());

        assertThatCode(() -> {
            // Act
            var simulation = reader.read(input);

            // Assert
            Assertions.assertThat(simulation).isNotNull();
        }).doesNotThrowAnyException();
    }

    @Test
    void readInvalidInput() {
        // Arrange
        var input = new ByteArrayInputStream(("5 5\n" +
                "1 2 N\n" +
                "XXXLFLFLFLFF").getBytes());

        assertThatThrownBy(() -> {
            // Act
            reader.read(input);
        })
                // Assert
                .isInstanceOf(ParsingException.class)
                .hasMessageContaining("X")
                .hasMessageContaining("NavigationInstruction");
    }
}