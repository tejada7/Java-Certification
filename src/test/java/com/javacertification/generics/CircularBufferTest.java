package com.javacertification.generics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.DisplayNameGenerator.*;

@DisplayName("Circular buffer")
@DisplayNameGeneration(IndicativeSentences.class)
@IndicativeSentencesGeneration(separator = " ", generator = ReplaceUnderscores.class)
class CircularBufferTest {

    private CircularBuffer buffer;

    @BeforeEach
    public void setUp() throws Exception {
        buffer = new CircularBuffer(3);
    }

    @Test
    void should_offer_pollable_element() {
        buffer.offer(1);
        assertEquals(1, buffer.poll());
    }

    @Test
    void shouldNotOfferWhenBufferIsFull() {
        buffer.offer(1);
        buffer.offer(2);
        buffer.offer(3);
        assertFalse(buffer.offer(4));
    }

    @Test
    void shouldNotPollWhenBufferIsEmpty() {
        assertNull(buffer.poll());
    }

    @Test
    void shouldRecycleBuffer() {
        assertTrue(buffer.offer(1));
        assertTrue(buffer.offer(2));
        assertEquals(1, buffer.poll());
        assertTrue(buffer.offer(3));
        assertEquals(2, buffer.poll());
        assertEquals(3, buffer.poll());
    }
}
