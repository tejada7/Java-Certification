package com.javacertification.leetcode;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

public class MedianOfTwoSortedArrays implements BiFunction<Integer[], Integer[], Double> {

    @Override
    public Double apply(final Integer[] firstArray, final Integer[] secondArray) {
        return findMedianSortedArrays(convertToNative(firstArray), convertToNative(secondArray));
    }

    private int[] convertToNative(final Integer[] arrayInWrapper) {
        return Arrays.stream(arrayInWrapper)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        final var size = nums1.length + nums2.length;
        return IntStream.concat(Arrays.stream(nums1), Arrays.stream(nums2))
                .sorted()
                .skip((size - 1) / 2)
                .limit(2 - size % 2)
                .average().orElseThrow(() -> new IllegalStateException("No element found in either input array."));
    }
}
