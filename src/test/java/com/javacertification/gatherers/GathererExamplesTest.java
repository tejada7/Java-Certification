package com.javacertification.gatherers;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.assertj.core.api.BDDAssertions.then;

@DisplayNameGeneration(ReplaceUnderscores.class)
class GathererExamplesTest {


    @Test
    void should_apply_upper_case() {
        // Given
        final List<String> input = List.of("one", "two", "three", "four", "five");

        // When
        final List<String> actual = input.stream().gather(GathererExamples.getToUpperCase()).toList();

        // Then
        then(actual).containsExactly("ONE", "TWO", "THREE", "FOUR", "FIVE");
    }

    @Test
    void should_apply_distinct() {
        // Given
        final IntStream randomStrings = new Random(217).ints(1_000, 0, 10);

        // When
        final List<Integer> actual = randomStrings
            .boxed()
            .gather(GathererExamples.distinct())
            .sorted()
            .toList();

        // Then
        then(actual).containsExactlyInAnyOrder(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    }

    @Test
    void should_sort_using_gatherer() {
        // Given
        final List<Integer> integers = List.of(3, 1, 2, 4, 5, 6, 7, 9, 8);

        // When
        final List<Integer> sorted = integers.stream().gather(GathererExamples.sort()).toList();

        // Then
        then(sorted).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9);
    }
}
