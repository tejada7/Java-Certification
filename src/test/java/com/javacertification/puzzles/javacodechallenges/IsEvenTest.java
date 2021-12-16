package com.javacertification.puzzles.javacodechallenges;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.function.Predicate;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenNullPointerException;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;

@DisplayNameGeneration(ReplaceUnderscores.class)
class IsEvenTest {

    private final Predicate<Number> isEven = new IsEven<>();

    @ParameterizedTest(name = "Testing isEven predicate with {0}.")
    @ValueSource(ints = {0, 2, 4, -6, -0})
    void should_return_true_when_odd_number(final Number input) {
        then(isEven).accepts(input);
    }

    @Test
    void should_throw_illegal_argument_exception_when_null_input() {
        thenNullPointerException()
                .isThrownBy(() -> isEven.test(null))
                .withMessage("number must be present.");
    }
}