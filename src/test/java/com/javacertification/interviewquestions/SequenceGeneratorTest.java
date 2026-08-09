package com.javacertification.interviewquestions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.javacertification.interviewquestions.SequenceGenerator.*;
import static org.assertj.core.api.BDDAssertions.thenIllegalArgumentException;
import static org.assertj.core.api.BDDSoftAssertions.thenSoftly;

class SequenceGeneratorTest {

    private static Stream<Arguments> fibonacciOutOfScope() {
        return Stream.of(
            Arguments.of(((Supplier<?>) () -> recursiveFibonacci(0))),
            Arguments.of(((Supplier<?>) () -> iterativeFibonacci(0))),
            Arguments.of(((Supplier<?>) () -> tailRecursiveFibonacci(0)))
        );
    }

    @ParameterizedTest
    @MethodSource
    void fibonacciOutOfScope(Supplier<?> approach) {
        thenIllegalArgumentException().isThrownBy(approach::get);
    }

    @Test
    void fibonacci() {
        thenSoftly(softly -> {
            softly.then(recursiveFibonacci(1)).isEqualTo(1);
            softly.then(recursiveFibonacci(2)).isEqualTo(1);
            softly.then(recursiveFibonacci(3)).isEqualTo(2);
            softly.then(recursiveFibonacci(4)).isEqualTo(3);
            softly.then(recursiveFibonacci(5)).isEqualTo(5);
            softly.then(recursiveFibonacci(6)).isEqualTo(8);
            softly.then(recursiveFibonacci(7)).isEqualTo(13);
        });

        thenSoftly(softly -> {
            softly.then(iterativeFibonacci(1)).isEqualTo(1);
            softly.then(iterativeFibonacci(2)).isEqualTo(1);
            softly.then(iterativeFibonacci(3)).isEqualTo(2);
            softly.then(iterativeFibonacci(4)).isEqualTo(3);
            softly.then(iterativeFibonacci(5)).isEqualTo(5);
            softly.then(iterativeFibonacci(6)).isEqualTo(8);
            softly.then(iterativeFibonacci(7)).isEqualTo(13);
        });

        thenSoftly(softly -> {
            softly.then(tailRecursiveFibonacci(1)).isEqualTo(1);
            softly.then(tailRecursiveFibonacci(2)).isEqualTo(1);
            softly.then(tailRecursiveFibonacci(3)).isEqualTo(2);
            softly.then(tailRecursiveFibonacci(4)).isEqualTo(3);
            softly.then(tailRecursiveFibonacci(5)).isEqualTo(5);
            softly.then(tailRecursiveFibonacci(6)).isEqualTo(8);
            softly.then(tailRecursiveFibonacci(7)).isEqualTo(13);
        });
    }
}
