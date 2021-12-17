package com.javacertification.puzzles.javacodechallenges;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.function.Predicate;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenNullPointerException;

class IsPasswordComplexEnoughTest {

    private final Predicate<String> isPasswordComplexEnough = new IsPasswordComplexEnough();

    @ParameterizedTest(name = "{index} - Testing with {0}.")
    @ValueSource(strings = {"aSdf23", "2Adlll", "Aw23eSX"})
    void should_accept_predicate_when_valid_complex_password(final String password) {
        then(isPasswordComplexEnough).accepts(password);
    }

    @ParameterizedTest(name = "{index} Testing with {0}.")
    @ValueSource(strings = {"aSdf", "2fdlll", "AWFDSX", "AsFgSX", "aS1 ew"})
    void should_reject_when_invalid_password(final String password) {
        then(isPasswordComplexEnough).rejects(password);
    }

    @Test
    void should_throw_illegal_argument_exception_when_null_input() {
        thenNullPointerException()
                .isThrownBy(() -> isPasswordComplexEnough.test(null))
                .withMessage("password must be present.");
    }
}