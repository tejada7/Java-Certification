package com.oca.interviewquestions;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayUtilMergeArraysTest {

    @Test
    public void shouldMergeAndSortArraysTest() {
        final Integer[][] input = {new Integer[]{1, 2, 3, 4, 5},
                new Integer[]{1, 2, 3, 4, 5},
                new Integer[]{6, 7, 8, 9, 10},
                new Integer[]{6, 2, 7, 1, 0}};
        final Integer[] expectedResult = {0, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 9, 10};
        Assert.assertTrue(Arrays.equals(ArrayUtil.mergeAndSortArrays(input), expectedResult));
    }

    @Test
    public void shouldMergeAndSortArray_whenProvidingComparableImplTest() {
        final Byte[][] input = {
                new Byte[]{1, 2, 3, 4, 5},
                new Byte[]{1, 2, 3, 4, 5},
                new Byte[]{6, 7, 8, 9, 10},
                new Byte[]{6, 2, 7, 1, 0}};
        final Byte[] expectedResult = {10, 9, 8, 7, 7, 6, 6, 5, 5, 4, 4, 3, 3, 2, 2, 2, 1, 1, 1, 0};
        Assert.assertTrue(Arrays.equals(ArrayUtil.mergeAndSortArrays(Comparator.reverseOrder(), input), expectedResult));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNullParameters_shouldThrowException() {
        ArrayUtil.mergeAndSortArrays(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoParameters_shouldThrowException() {
        ArrayUtil.mergeAndSortArrays();
    }
}
