package com.javacertification.ocp.io;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class InputOutputTest {

    InputOutput inputOutput = new InputOutput();

    @Test
    void shouldCreateFileOrDirectory() throws IOException {
        // Given
        var directoryPath = Path.of("newDirectory");

        // When
        inputOutput.createFileOrDirectory(directoryPath);

        // Then
        assertTrue(directoryPath.toFile().exists());
    }

    @Test
    void shouldWriteInFile() throws IOException {
        // Given
        var lines = List.of("Line 1", "Line 2", "Line 3");
        var file = Path.of("hello.txt");

        // When
        inputOutput.createFileOrDirectory(file);
        inputOutput.writeDataInFile(lines, file);

        // Then
        BufferedReader br = Files.newBufferedReader(file);
        assertEquals(Files.readAllLines(file), lines);
    }

    @Test
    void shouldSerialize() throws IOException, ClassNotFoundException {
        // Given
        InputOutput.Foo foo = new InputOutput.Foo(1, "name");

        // When
        inputOutput.serializeObject(foo, "object");

        // Then
        var actual = inputOutput.deserializeObject("object", InputOutput.Foo.class);
        assertEquals("name", actual.get(0).getName());
        assertEquals(1, actual.get(0).getId());
        assertFalse(actual.get(0).isFlag());
    }

    @Test
    void shouldGetConsoleUsingScannerImpl() {
        // Given
        var input = "Something";
        var scannerImpl = new InputOutput.ScannerImpl(input);

        // When
        var actual = scannerImpl.readLine("Please input something to the console...");

        // Then
        assertEquals(input, actual);
        assertThrows(NoSuchElementException.class, scannerImpl::readLine);
    }

    @Test
    void shouldThrowExceptionWhen_AttemptingToGetConsoleFromUnitTest() {
        assertThrows(IllegalStateException.class, InputOutput.ConsoleImpl::new);
    }

    @Test
    void shouldGetBufferReaderImplUsingStringAsInput() {
        // Given
        var input = "Something";
        var bufferReaderImpl = new InputOutput.BufferReaderImpl(input);

        // When
        var actual = bufferReaderImpl.readLine("Please input something to the console...");

        // Then
        assertEquals(input, actual);
        assertNull(bufferReaderImpl.readLine());
    }

}