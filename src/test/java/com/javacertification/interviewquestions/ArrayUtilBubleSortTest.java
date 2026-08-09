package com.javacertification.interviewquestions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenIllegalArgumentException;

class ArrayUtilBubleSortTest {

    private static Stream<Arguments> getData() {
        return Stream.of(
            Arguments.of(new Integer[]{5, 4, 5, 3, 0, 4, 1}, new Integer[]{0, 1, 3, 4, 4, 5, 5}),
            Arguments.of(new String[]{"a", "f", "c"}, new String[]{"a", "c", "f"}),
            Arguments.of(new Float[]{5f, 4.3f, 4.1f, 5.6f}, new Float[]{4.1f, 4.3f, 5f, 5.6f}),
            Arguments.of(new Double[]{5.4, 10.9, 0d}, new Double[]{0d, 5.4, 10.9})
        );
    }

    @ParameterizedTest
    @MethodSource("getData")
    void testBubbleSort(Comparable<?>[] input, Comparable<?>[] expectedOutput) {
        // When
        ArrayUtil.bubbleSort(input);

        // Then
        then(input).containsExactly(expectedOutput);
    }

    @Test
    void testBubbleSort_whenNullArgument_shouldThrowIllegalArgumentException() {
        thenIllegalArgumentException().isThrownBy(() -> ArrayUtil.bubbleSort(null));
    }
}
