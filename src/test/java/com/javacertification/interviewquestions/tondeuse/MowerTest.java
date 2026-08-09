package com.javacertification.interviewquestions.tondeuse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MowerTest {

    /**
     * Dataset.
     *
     * @return a collection that contains the value to which we evaluate to know if it belong to a power of 2 and the
     * expected result.
     */
    private static Stream<Arguments> mowerTestFromCodeInput() {
        return Stream.of(
            Arguments.of(
                asList("5 5",
                    "1 2 N",
                    "GAGAGAGAA",
                    "3 3 E",
                    "AADAADADDA"),
                Arrays.asList("1 3 N", "5 1 E")
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void mowerTestFromCodeInput(List<String> input, List<String> expectedOutput) {
        assertEquals(expectedOutput, MowerLauncher.processInput(input, false));
    }

    @Test
    void mowerTestFromExternalFile() throws URISyntaxException, IOException {
        // Given
        final URL resource = getClass().getClassLoader().getResource("mower_test_case_1.txt");

        // When
        List<String> lines = Files.readAllLines(Paths.get(resource.toURI()));

        // Then
        assertEquals(Arrays.asList("1 3 N", "5 1 E"), MowerLauncher.processInput(lines, false));
    }
}
