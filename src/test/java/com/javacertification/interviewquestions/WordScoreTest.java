package com.javacertification.interviewquestions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.thenIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WordScoreTest {

    private static final String LONG_STRING = "I don't mind\n" +
        "I don't care\n" +
        "As long as you're here\n" +
        "Go ahead, tell me you'll leave again\n" +
        "You'll just come back running\n" +
        "Holding your scarred heart in hand\n" +
        "It's all the same\n" +
        "And I'll take you for who you are\n" +
        "If you take me for everything\n" +
        "And do it all over again\n" +
        "It's all the same\n" +
        "Hours slide and days go by\n" +
        "'Til you decide to come\n" +
        "But in-between\n" +
        "It always seems\n" +
        "Too long for certain";

    private static Stream<Arguments> data() {
        return Stream.of(
            Arguments.of("XRay Machine", 20),
            Arguments.of("Jabbt", 13)
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    public void testWordScore(String input, int expectedScore) {
        assertEquals(expectedScore, new WordScore().applyAsInt(input));
    }

    @Test
    void testWordScore_shouldThrowException_whenNullInput() {
        assertThrows(IllegalArgumentException.class, () -> new WordScore().applyAsInt(null));
    }

    @Test
    public void testWordScore_shouldThrowException_whenInputGreaterThan50() {
        thenIllegalArgumentException()
            .isThrownBy(() -> new WordScore().applyAsInt(LONG_STRING));
    }
}
