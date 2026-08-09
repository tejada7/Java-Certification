package com.javacertification.interviewquestions;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;

class PowerOfTwoPredicateTest {

    private final IsPowerOfTwo isPowerOfTwo = new IsPowerOfTwo();

    /**
     * Dataset.
     *
     * @return a collection that contains the value to which we evaluate to know if it belongs to a power of 2 and the
     * expected result.
     */
    private static Stream<Arguments> data() {
        return Stream.of(
            Arguments.of(4, true),
            Arguments.of(27, false),
            Arguments.of(124, false),
            Arguments.of(0, true)
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    void powerOfTest(int input, boolean expectedOutput) {
        then(isPowerOfTwo.test(input)).isEqualTo(expectedOutput);
    }
}
