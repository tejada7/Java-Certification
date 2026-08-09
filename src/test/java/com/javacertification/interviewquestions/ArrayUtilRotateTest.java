package com.javacertification.interviewquestions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenIllegalArgumentException;

class ArrayUtilRotateTest {

    static Stream<Arguments> getData() {
        final Integer[][] inputMatrix = {{1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20},
            {21, 22, 23, 24, 25}};
        final Integer[][] outputMatrix = {{21, 16, 11, 6, 1},
            {22, 17, 12, 7, 2},
            {23, 18, 13, 8, 3},
            {24, 19, 14, 9, 4},
            {25, 20, 15, 10, 5}};
        final String[][] inputMatrix1 = {{"a", "b"}, {"c", "d"}};
        final String[][] outputMatrix1 = {{"c", "a"}, {"d", "b"}};
        return Stream.of(
            Arguments.of(inputMatrix, outputMatrix),
            Arguments.of(inputMatrix1, outputMatrix1)
        );
    }

    @ParameterizedTest
    @MethodSource("getData")
    void testRotateMatrix(Comparable[][] input, Comparable[][] expectedOutput) {
        final Object[][] result = ArrayUtil.rotateMatrix(input);
        for (int i = 0; i < input.length; i++) {
            then(result[i]).isEqualTo(expectedOutput[i]);
        }
    }

    @Test
    void testRotareMatrix_whenNullArgument_shouldThrowIllegalArgumentException() {
        thenIllegalArgumentException().isThrownBy(() -> ArrayUtil.rotateMatrix(null));
    }
}
