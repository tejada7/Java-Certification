package com.javacertification.interviewquestions;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TopFrequentTest {
    private Integer[] array = new Integer[]{3, 3, 0, 3, 3, 1, 2, 0, 1, 1, 1, 3, 2, 2, 4};

    @Test
    void emptyArray() {
        assertArrayEquals(new Integer[0], ArrayUtil.kFrequentElements(new Integer[0], 3));
    }

    void top0() {
        BDDAssertions.thenIllegalArgumentException()
            .isThrownBy(() -> ArrayUtil.kFrequentElements(null, 0));
    }

    @Test
    void top1() {
        assertArrayEquals(new Integer[]{3}, ArrayUtil.kFrequentElements(array, 1));
    }

    @Test
    void top2() {
        assertArrayEquals(new Integer[]{3, 1}, ArrayUtil.kFrequentElements(array, 2));
    }

    @Test
    void top3() {
        assertArrayEquals(new Integer[]{3, 1, 2}, ArrayUtil.kFrequentElements(array, 3));
    }

    @Test
    void top10() {
        assertArrayEquals(new Integer[]{3, 1, 2, 0, 4}, ArrayUtil.kFrequentElements(array, 10));
    }
}
