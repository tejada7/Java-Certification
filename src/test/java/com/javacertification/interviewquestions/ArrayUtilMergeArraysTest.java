package com.javacertification.interviewquestions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenIllegalArgumentException;

class ArrayUtilMergeArraysTest {

    @Test
    void shouldMergeAndSortArraysTest() {
        // Given
        final Integer[][] input = {new Integer[]{1, 2, 3, 4, 5},
            new Integer[]{1, 2, 3, 4, 5},
            new Integer[]{6, 7, 8, 9, 10},
            new Integer[]{6, 2, 7, 1, 0}};

        // When
        final List<Integer> actual = ArrayUtil.mergeAndSortArrays(input);

        // Then
        then(actual)
            .containsExactly(0, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 9, 10);
    }

    @Test
    void shouldMergeAndSortArray_whenProvidingComparableImpl() {
        // Given
        final Byte[][] input = {
            new Byte[]{1, 2, 3, 4, 5},
            new Byte[]{1, 2, 3, 4, 5},
            new Byte[]{6, 7, 8, 9, 10},
            new Byte[]{6, 2, 7, 1, 0}};

        // When
        final List<Byte> actual = ArrayUtil.mergeAndSortArrays(Comparator.reverseOrder(), input);

        // Then
        then(actual)
            .containsExactly(new Byte[]{10, 9, 8, 7, 7, 6, 6, 5, 5, 4, 4, 3, 3, 2, 2, 2, 1, 1, 1, 0});
    }

    @Test
    void whenNullOrEmptyParameters_shouldThrowException() {
        thenIllegalArgumentException().isThrownBy(ArrayUtil::mergeAndSortArrays);
        thenIllegalArgumentException().isThrownBy(() -> ArrayUtil.mergeAndSortArrays(null));
    }
}
