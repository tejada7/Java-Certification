package com.javacertification.interviewquestions;

import com.javacertification.interviewquestions.FizzBuzz.Input;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.assertj.core.api.BDDAssertions.thenIllegalArgumentException;
import static org.assertj.core.api.BDDSoftAssertions.thenSoftly;

class FizzBuzzTest {

    private final Function<Input, List<String>> fizzBuzz = new FizzBuzz();

    @Test
    void should_print_first_100_elements() {
        // Given
        final Consumer<String> consumer = System.out::println;
        final var input = Input.of(1, 100);

        // When
        final List<String> actual = fizzBuzz.apply(input);
        actual.forEach(consumer);

        // Then
        thenSoftly(softly -> {
            softly.then(actual).hasSize(100);
            softly.then(actual.get(2)).isEqualTo("Fizz");
            softly.then(actual.get(4)).isEqualTo("Buzz");
            softly.then(actual.get(89)).isEqualTo("FizzBuzz");
        });
    }

    @Test
    void should_throw_exception_when_invalid_input() {
        thenIllegalArgumentException().isThrownBy(() -> Input.of(10, 0));
    }
}