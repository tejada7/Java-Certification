package com.oca.interviewquestions;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TopFrequentTest {
    private Integer[] array = new Integer[]{3, 3, 0, 3, 3, 1, 2, 0, 1, 1, 1, 3, 2, 2, 4};

    @Test
    public void emptyArray() {
        assertArrayEquals(new Integer[0], ArrayUtil.kFrequentElements(new Integer[0], 3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void top0() {
        ArrayUtil.kFrequentElements(null, 0);
    }

    @Test
    public void top1() {
        assertArrayEquals(new Integer[]{3}, ArrayUtil.kFrequentElements(array, 1));
    }

    @Test
    public void top2() {
        assertArrayEquals(new Integer[]{3, 1}, ArrayUtil.kFrequentElements(array, 2));
    }

    @Test
    public void top3() {
        assertArrayEquals(new Integer[]{3, 1, 2}, ArrayUtil.kFrequentElements(array, 3));
    }

    @Test
    public void top10() {
        assertArrayEquals(new Integer[]{3, 1, 2, 0, 4}, ArrayUtil.kFrequentElements(array, 10));
    }
}
