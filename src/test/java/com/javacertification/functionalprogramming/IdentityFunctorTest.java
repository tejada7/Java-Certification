package com.javacertification.functionalprogramming;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class IdentityFunctorTest {

    @Test
    void should_map() {
        // Given
        final var input = "hello word";
        final var functor = IdentityFunctor.of(input);

        // When
        final IdentityFunctor<Integer> actual = functor.map(String::length);

        // Then
        then(actual.value()).isEqualTo(10);
    }
}
