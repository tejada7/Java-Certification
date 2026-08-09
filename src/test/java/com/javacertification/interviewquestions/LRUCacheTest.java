package com.javacertification.interviewquestions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDSoftAssertions.thenSoftly;

class LRUCacheTest {

    private final LRUCache<Integer, Integer> cache = new LRUCache<>(4);

    @Test
    void evictLeastRecentlyUsed() {
        // given
        cache.set(1, 11);
        cache.set(2, 22);
        cache.set(3, 33);
        cache.set(4, 44);

        // when
        cache.set(5, 55);

        // then
        thenSoftly(softly -> {
            softly.then(cache.get(1)).isNull();
            softly.then(cache.get(2)).isEqualTo(22);
            softly.then(cache.get(3)).isEqualTo(33);
            softly.then(cache.get(4)).isEqualTo(44);
            softly.then(cache.get(5)).isEqualTo(55);

        });

        subTest_1();
        subTest_2();
        subTest_3();
    }

    void subTest_1() {
        // given
        // when
        cache.set(6, 66);

        // then
        thenSoftly(softly -> {
            softly.then(cache.get(1)).isNull();
            softly.then(cache.get(2)).isNull();
            softly.then(cache.get(3)).isEqualTo(33);
            softly.then(cache.get(4)).isEqualTo(44);
            softly.then(cache.get(5)).isEqualTo(55);
            softly.then(cache.get(6)).isEqualTo(66);

        });
    }

    void subTest_2() {
        // given
        // when
        cache.set(7, 77);

        // then
        thenSoftly(softly -> {
            softly.then(cache.get(1)).isNull();
            softly.then(cache.get(2)).isNull();
            softly.then(cache.get(3)).isNull();
            softly.then(cache.get(4)).isEqualTo(44);
            softly.then(cache.get(5)).isEqualTo(55);
            softly.then(cache.get(6)).isEqualTo(66);
            softly.then(cache.get(7)).isEqualTo(77);
        });
    }

    void subTest_3() {
        // given
        // when
        cache.set(8, 88);

        // then
        thenSoftly(softly -> {
            softly.then(cache.get(1)).isNull();
            softly.then(cache.get(2)).isNull();
            softly.then(cache.get(3)).isNull();
            softly.then(cache.get(4)).isNull();
            softly.then(cache.get(5)).isEqualTo(55);
            softly.then(cache.get(6)).isEqualTo(66);
            softly.then(cache.get(7)).isEqualTo(77);
            softly.then(cache.get(8)).isEqualTo(88);
        });
    }
}
