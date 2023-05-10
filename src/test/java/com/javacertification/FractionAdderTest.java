package com.javacertification;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

@DisplayNameGeneration(ReplaceUnderscores.class)
class FractionAdderTest {

    @Test
    void should_add_and_simplify_the_addition_of_two_fractions() {
        // Given
        final var first = Fraction.of("1/2");
        final var second = Fraction.of("3/4");

        // When
        final var actual = Fraction.add()
                                   .andThen(Fraction.simplify())
                                   .apply(first, second);

        // Then
        final Fraction expected = Fraction.of(1, "1/4");
        then(actual).isEqualTo(expected);
    }
}
