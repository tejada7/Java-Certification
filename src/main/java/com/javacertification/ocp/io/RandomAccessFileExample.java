package com.javacertification.ocp.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;

public class RandomAccessFileExample {

    /**
     * Reads a file using {@link RandomAccessFile} and returns its content in a {@link String} object.
     *
     * @param file the existing file to read
     * @param mode the valid modes (i.e. 'r', 'rw', 'rws', 'rwd')
     * @return a {@link String} object with its content
     */
    public String readFile(final Path file, final String mode) {
        try (var raf = new RandomAccessFile(file.toFile(), mode)) {
            var sb = new StringBuilder();
            while (raf.getFilePointer() < raf.length()) {
                sb.append(raf.readLine());
                sb.append(System.lineSeparator());
            }
            return sb.toString();
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e);
        }
        return null;
    }
}
