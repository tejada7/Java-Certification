package com.javacertification.interviewquestions;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VersionNumberComparatorTest {

    private static Stream<Arguments> data() {
        return Stream.of(
            Arguments.of("2.2.5", "2.3", -1),
            Arguments.of("3.0", "2.1.5.3", 1),
            Arguments.of("3", "4.0", -1),
            Arguments.of("12.5.1", "12.5.2", -1),
            Arguments.of("12.5.1", "12.5.1", 0),
            Arguments.of("12.6.1", "12.5.1", 1),
            Arguments.of("14.10.55", "14.10.20", 1),
            Arguments.of("14.13.10", "14.10.55", 1),
            Arguments.of("14.13.10", "15.1", -1)
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    public void compareVersionTest(String version1, String version2, int expected) {
        assertEquals(expected, new VersionNumberComparator().compare(version1, version2));
    }
}
