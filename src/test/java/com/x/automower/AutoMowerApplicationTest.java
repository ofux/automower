package com.x.automower;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class AutoMowerApplicationTest {

    private PrintStream stdOutStream;
    private ByteArrayOutputStream capturedStream;

    @BeforeEach
    void setUp() {
        stdOutStream = System.out;
        capturedStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturedStream));
    }

    @AfterEach
    void tearDown() throws IOException {
        if (capturedStream != null) {
            capturedStream.close();
            capturedStream = null;
        }
        System.setOut(stdOutStream);
    }

    @Test
    void main() throws IOException {
        // Arrange
        var url = getClass().getClassLoader().getResource("input1.txt");
        assert url != null;

        // Act
        AutoMowerApplication.main(new String[]{url.getFile()});

        // Assert
        System.setOut(stdOutStream);
        capturedStream.flush();
        assertThat(capturedStream.toString().trim()).isEqualTo("1 3 N\n5 1 E");
    }
}