package com.x.automower.service;

import com.x.automower.parsing.OrientationParser;
import com.x.automower.parsing.TransformParser;
import com.x.automower.parsing.Vector2DParser;
import com.x.automower.simulation.Mower;
import com.x.automower.simulation.math.Orientation;
import com.x.automower.simulation.math.Transform;
import com.x.automower.simulation.math.Vector2D;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class ConsoleMowerRendererTest {

    private ConsoleMowerRenderer renderer;
    private PrintStream stdOutStream;
    private ByteArrayOutputStream capturedStream;

    @BeforeEach
    void setUp() {
        stdOutStream = System.out;
        capturedStream = new ByteArrayOutputStream();
        renderer = new ConsoleMowerRenderer(new TransformParser(new Vector2DParser(), new OrientationParser()));
        System.setOut(new PrintStream(capturedStream));
    }

    @AfterEach
    void tearDown() throws IOException {
        if (capturedStream != null) {
            capturedStream.close();
        }
        System.setOut(stdOutStream);
    }

    @Test
    void render() throws IOException {
        // Act
        renderer.render(new Mower(new Transform(new Vector2D(12, 4), Orientation.EAST), new ArrayList<>()));

        // Assert
        System.setOut(stdOutStream);
        capturedStream.flush();
        assertThat(capturedStream.toString().trim()).isEqualTo("12 4 E");
    }
}