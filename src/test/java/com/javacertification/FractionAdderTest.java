package com.javacertification;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

@DisplayNameGeneration(ReplaceUnderscores.class)
class FractionAdderTest {

    @Test
    void should_add_two_fractions() {
        // Given
        final var first = Fraction.of("1/2");
        final var second = Fraction.of("3/4");

        // When
        final Fraction actual = first.add(second);

        // Then
        // final Fraction expected = Fraction.of(1, "1/4");
        final Fraction expected = Fraction.of(0, "5/4");
        then(actual).isEqualTo(expected);
    }
}
