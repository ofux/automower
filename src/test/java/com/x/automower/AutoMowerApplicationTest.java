package com.x.automower;

import com.x.automower.parsing.ParsingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    void endToEndWithSpecInput() throws IOException {
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

    @Test
    void endToEndWithEmptyNavigationInstruction() throws IOException {
        // Arrange
        var url = getClass().getClassLoader().getResource("input2.txt");
        assert url != null;

        // Act
        AutoMowerApplication.main(new String[]{url.getFile()});

        // Assert
        System.setOut(stdOutStream);
        capturedStream.flush();
        assertThat(capturedStream.toString().trim()).isEqualTo("1 2 N\n5 1 E");
    }

    @Test
    void endToEndWithSingleCellLawn() throws IOException {
        // Arrange
        var url = getClass().getClassLoader().getResource("input3.txt");
        assert url != null;

        // Act
        AutoMowerApplication.main(new String[]{url.getFile()});

        // Assert
        System.setOut(stdOutStream);
        capturedStream.flush();
        assertThat(capturedStream.toString().trim()).isEqualTo("0 0 N");
    }

    @Test
    void endToEndWithBadLawnSize() {
        // Arrange
        var url = getClass().getClassLoader().getResource("input4.txt");
        assert url != null;

        assertThatThrownBy(() -> {
            // Act
            AutoMowerApplication.main(new String[]{url.getFile()});
        })
                // Assert
                .isInstanceOf(ParsingException.class)
                .hasMessageContaining("-1 0")
                .hasMessageContaining("Vector2D");
    }

    @Test
    void endToEndWithNoMower() throws IOException {
        // Arrange
        var url = getClass().getClassLoader().getResource("input5.txt");
        assert url != null;

        // Act
        AutoMowerApplication.main(new String[]{url.getFile()});

        // Assert
        System.setOut(stdOutStream);
        capturedStream.flush();
        assertThat(capturedStream.toString().trim()).isEqualTo("");
    }

    @Test
    void endToEndWithOverlappingMowers() {
        // Arrange
        var url = getClass().getClassLoader().getResource("input6.txt");
        assert url != null;

        assertThatThrownBy(() -> {
            // Act
            AutoMowerApplication.main(new String[]{url.getFile()});
        })
                // Assert
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void endToEndWithMowerOutsideLawn() {
        // Arrange
        var url = getClass().getClassLoader().getResource("input7.txt");
        assert url != null;

        assertThatThrownBy(() -> {
            // Act
            AutoMowerApplication.main(new String[]{url.getFile()});
        })
                // Assert
                .isInstanceOf(IllegalStateException.class);
    }
}