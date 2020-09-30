package com.javacertification.generics;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CircularBufferTest {

    private CircularBuffer buffer;

    @Before
    public void setUp() throws Exception {
        buffer = new CircularBuffer(3);
    }

    @Test
    public void shouldOfferPollableElement() {
        buffer.offer(1);
        assertEquals(1, buffer.poll());
    }

    @Test
    public void shouldNotOfferWhenBufferIsFull() {
        buffer.offer(1);
        buffer.offer(2);
        buffer.offer(3);
        assertFalse(buffer.offer(4));
    }

    @Test
    public void shouldNotPollWhenBufferIsEmpty() {
        assertNull(buffer.poll());
    }

    @Test
    public void shouldRecycleBuffer() {
        assertTrue(buffer.offer(1));
        assertTrue(buffer.offer(2));
        assertEquals(1, buffer.poll());
        assertTrue(buffer.offer(3));
        assertEquals(2, buffer.poll());
        assertEquals(3, buffer.poll());
    }
}