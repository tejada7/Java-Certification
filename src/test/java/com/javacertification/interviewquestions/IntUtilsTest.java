package com.javacertification.interviewquestions;

import com.javacertification.interviewquestions.model.Fruit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static com.javacertification.interviewquestions.IntUtils.*;
import static com.javacertification.interviewquestions.model.Fruit.*;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.valueOf;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDSoftAssertions.thenSoftly;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IntUtilsTest {

    private static void execute() {
        factorial(-1);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
        4, 2, true
        27, 3, true
        124, 5, false
        """)
    void shouldBePowerOf(int number, int powerOf, boolean expected) {
        then(isPowerOf(number, powerOf)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
        12321, true
        123, false
        """)
    void shouldBePalindrome(int number, boolean expected) {
        then(isPalindrome(number)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
        153, true
        370, true
        1634, true
        9474, true
        1, true
        1000, false
        """)
    void shouldBeArmstrongNumber(int number, boolean expected) {
        then(isArmstrong(number)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
        23, true
        193, true
        761, true
        4, false
        737, false
        """)
    void shouldBeHappyNumber(int number, boolean expected) {
        then(isHappy(number)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
        7, true
        12, false
        """)
    void shouldBePrime(int number, boolean expected) {
        then(isPrime(number)).isEqualTo(expected);
    }

    @Test
    void shouldBePairCombinationNumber() {
        then(getFruitPairCombinationsNumber(Set.of(APPLE, BANANA, ORANGE, PEAR)))
            .isEqualTo(6);
    }

    @Test
    public void shouldPairCombineElements() {
        // Given
        final List<Fruit> fruits = List.of(APPLE, BANANA, ORANGE, PEAR);

        // When
        final List<Set<Fruit>> result = getFruitPairCombinationsElements(fruits);

        // Then
        thenSoftly(softly -> {
            softly.then(result).hasSize(6)
                .containsExactly(
                    of(APPLE, BANANA),
                    of(APPLE, ORANGE),
                    of(APPLE, PEAR),
                    of(BANANA, ORANGE),
                    of(BANANA, PEAR),
                    of(ORANGE, PEAR)
                );

        });
    }

    private static Stream<Arguments> shouldComputeFactorial() {
        return Stream.of(
            Arguments.of(0, ONE),
            Arguments.of(1, ONE),
            Arguments.of(5, BigInteger.valueOf(120)),
            Arguments.of(8, BigInteger.valueOf(40320))
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldComputeFactorial(int number, BigInteger expected) {
        then(factorial(number)).isEqualTo(expected);
    }

    @Test
    void shouldThrowExceptionWhenInvalidFactorialInput() {
        Assertions.assertThrows(IllegalArgumentException.class, IntUtilsTest::execute);
    }
}
