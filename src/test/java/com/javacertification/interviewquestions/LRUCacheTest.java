package com.javacertification.interviewquestions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LRUCacheTest {

    LRUCache<Integer, Integer> cache = new LRUCache<>(4);

    @Test
    public void evictLeastRecentlyUsed() {
        // given
        cache.set(1, 11);
        cache.set(2, 22);
        cache.set(3, 33);
        cache.set(4, 44);

        // when
        cache.set(5, 55);

        // then
        assertNull(cache.get(1));

        assertEquals(22, cache.get(2).intValue());
        assertEquals(33, cache.get(3).intValue());
        assertEquals(44, cache.get(4).intValue());
        assertEquals(55, cache.get(5).intValue());

        subTest_1();
        subTest_2();
        subTest_3();
    }

    public void subTest_1() {
        // given
        // when
        cache.set(6, 66);

        // then
        assertNull(cache.get(1));
        assertNull(cache.get(2));

        assertEquals(33, cache.get(3).intValue());
        assertEquals(44, cache.get(4).intValue());
        assertEquals(55, cache.get(5).intValue());
        assertEquals(66, cache.get(6).intValue());
    }

    private void subTest_2() {
        // given
        // when
        cache.set(7, 77);

        // then
        assertNull(cache.get(1));
        assertNull(cache.get(2));
        assertNull(cache.get(3));

        assertEquals(44, cache.get(4).intValue());
        assertEquals(55, cache.get(5).intValue());
        assertEquals(66, cache.get(6).intValue());
        assertEquals(77, cache.get(7).intValue());
    }

    private void subTest_3() {
        // given
        // when
        cache.set(8, 88);

        // then
        assertNull(cache.get(1));
        assertNull(cache.get(2));
        assertNull(cache.get(3));
        assertNull(cache.get(4));

        assertEquals(55, cache.get(5).intValue());
        assertEquals(66, cache.get(6).intValue());
        assertEquals(77, cache.get(7).intValue());
        assertEquals(88, cache.get(8).intValue());
    }
}