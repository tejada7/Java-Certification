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
public class ArrayUtilBubleSortTest {

    @Parameters
    public static Collection getData() {
        return asList(new Object[][]{
                {new Integer[]{5, 4, 5, 3, 0, 4, 1}, new Integer[]{0, 1, 3, 4, 4, 5, 5}},
                {new String[]{"a", "f", "c"}, new String[]{"a", "c", "f"}},
                {new Float[]{5f, 4.3f, 4.1f, 5.6f}, new Float[]{4.1f, 4.3f, 5f, 5.6f}},
                {new Double[]{5.4, 10.9, 0d}, new Double[]{0d, 5.4, 10.9}}
        });
    }

    @Parameter
    public Comparable[] input;

    @Parameter(value = 1)
    public Comparable[] expectedOutput;

    @Test
    public void testBubbleSort() {
        // When
        ArrayUtil.bubbleSort(input);

        // Then
        Assert.assertTrue(Arrays.equals(expectedOutput, input));
    }

    @Test
    public void testBubbleSort_whenNullArgument_shouldThrowIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArrayUtil.bubbleSort(null));
    }
}
