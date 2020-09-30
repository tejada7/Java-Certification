package com.javacertification.interviewquestions;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ArrayUtilRotateTest {

    @Parameters
    public static Collection getData() {
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
        return asList(new Object[][]{{inputMatrix, outputMatrix},
                {inputMatrix1, outputMatrix1}});
    }

    @Parameter
    public Comparable[][] input;

    @Parameter(value = 1)
    public Comparable[][] expectedOutput;

    @Test
    public void testRotateMatrix() {
        final Object[][] result = ArrayUtil.rotateMatrix(input);
        for (int i = 0; i < input.length; i++) {
            Assert.assertTrue(Arrays.equals(expectedOutput[i], result[i]));
        }
    }

    @Test
    public void testRotareMatrix_whenNullArgument_shouldThrowIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArrayUtil.rotateMatrix(null));
    }
}
