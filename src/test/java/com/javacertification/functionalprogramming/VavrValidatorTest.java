package com.javacertification.functionalprogramming;

import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.javacertification.functionalprogramming.VavrValidator.Foo;
import static com.javacertification.functionalprogramming.VavrValidator.FooValidator;
import static org.assertj.core.api.BDDSoftAssertions.thenSoftly;

class VavrValidatorTest {

    @Test
    void should_instantiate_pojo_when_valid_fields() {
        // Given
        final var name = "John";
        final var age = 29;
        final var birthDate = LocalDate.of(1978, 1, 1);
        final var expected = new Foo(name, age, birthDate);

        // When
        final Validation<Seq<String>, Foo> validation = FooValidator.validateFoo(name, age, birthDate);

        // Then
        thenSoftly(softly -> {
            softly.then(validation.isValid()).isTrue();
            softly.then(validation.get()).isEqualTo(expected);
        });
    }

    @Test
    void should_retrieve_error_when_invalid_pojo() {
        // Given
        final String name = null;
        final var age = -5;
        final var birthDate = LocalDate.of(3000, 1, 1);

        // When
        final Validation<Seq<String>, Foo> validation = FooValidator.validateFoo(name, age, birthDate);

        // Then
        thenSoftly(softly -> {
            softly.then(validation.isInvalid()).isTrue();
            softly.then(validation.getError())
                    .containsExactlyInAnyOrder("name cannot be empty",
                            "the age must be greater than 0 and less than 120",
                            "Incoherent birth date");
        });
    }
}