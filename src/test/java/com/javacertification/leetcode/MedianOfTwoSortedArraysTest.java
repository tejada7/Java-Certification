package com.javacertification.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;

class MedianOfTwoSortedArraysTest {

    private MedianOfTwoSortedArrays medianOfTwoSortedArrays = new MedianOfTwoSortedArrays();


    @ParameterizedTest
    @MethodSource("dataset")
    void should_return_median_of_two_sorted_arrays(final Integer[] firstArray, final Integer[] secondArray,
                                                   final double expected) {
        then(medianOfTwoSortedArrays.apply(firstArray, secondArray))
                .isEqualTo(expected);
    }

    private static Stream<Arguments> dataset() {
        return Stream.of(
                Arguments.of(new Integer[]{1, 3}, new Integer[]{2}, 2d),
                Arguments.of(new Integer[]{1, 2}, new Integer[]{3, 4}, 2.5d)
        );
    }

}
