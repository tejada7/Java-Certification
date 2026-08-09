package com.javacertification.interviewquestions;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;

class ReverseTextTest {

    private static Stream<Arguments> data() {
        return Stream.of(
            Arguments.of("", null),
            Arguments.of("", ""),
            Arguments.of("oruro", "oruro"),
            Arguments.of("012345", "543210"),
            Arguments.of("sDerG", "GreDs")
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    void reverseText(String expected, String input) {
        then(StringUtils.reverse(input)).isEqualTo(expected);
    }
}
