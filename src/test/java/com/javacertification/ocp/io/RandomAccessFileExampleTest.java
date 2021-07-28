package com.javacertification.ocp.io;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RandomAccessFileExampleTest {

    RandomAccessFileExample raf = new RandomAccessFileExample();

    @Test
    void shouldReadExistingFile() {
        // Given
        var file = Path.of("foo.txt");
        createFile(file);

        // When
        var actual = raf.readFile(file, "rw");

        // Then
        System.out.println(actual);
        assertEquals("First line" + System.lineSeparator() + "Second line" + System.lineSeparator(), actual);
    }

    private void createFile(Path file) {
        try (var bw = Files.newBufferedWriter(file)) {
            bw.write("First line");
            bw.newLine();
            bw.write("Second line");
        } catch (IOException e) {

        }
    }
}