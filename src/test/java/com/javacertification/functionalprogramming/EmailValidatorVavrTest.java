package com.javacertification.functionalprogramming;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.function.Function;

import static org.assertj.core.api.BDDSoftAssertions.thenSoftly;

class EmailValidatorVavrTest {

    private Function<String, Result<String>> emailValidator = new EmailValidatorVavr();

    @Test
    void should_return_success_when_valid_email() {
        // Given
        final var email = "test@test.com";

        // When
        final Result<String> result = emailValidator.apply(email);

        // Then
        thenSoftly(softly -> {
                    softly.then(result.isSuccess()).isTrue();
                    softly.then(result.getValue()).contains(email);
                }
        );
    }

    @ParameterizedTest(name = "{index} - Testing with {0}")
    @NullAndEmptySource
    void should_return_failure_result_when_empty_email(final String email) {
        // When
        final Result<String> result = emailValidator.apply(email);

        // Then
        thenSoftly(softly -> {
                    softly.then(result.isFailure()).isTrue();
                    softly.then(result.getError()).contains("email must not be empty");
                }
        );
    }
}