package com.oca.datastructures;

import com.oca.miscellaneous.RandomHelper;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Simple generic randomized queue implementation, whenever a dequeue is made, a random element is returned and the queue
 * is resized accordingly.
 *
 * @param <T> the generic type of the queue
 */
public class RandomizedQueue<T> implements Iterable<T> {

    private int size;
    private T[] data;
    private static final int DEFAULT_CAPACITY = 1;

    /**
     * Construct an empty randomized queue
     */
    public RandomizedQueue() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Is the randomized queue empty?
     *
     * @return a <code>boolean</code> value
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return the number of items on the randomized queue.
     *
     * @return an <code>int</code> value
     */
    public int size() {
        return size;
    }

    /**
     * Add an item.
     *
     * @param item the element to be added
     */
    public void enqueue(T item) {
        if (item == null) {
            throw new IllegalArgumentException("No null element can be added to the queue.");
        }
        data[size++] = item;
        if (size == data.length) {
            resize(2 * data.length);
        }
    }

    private void resize(int size) {
        T[] newData = (T[]) new Object[size];
        System.arraycopy(data, 0, newData, 0, this.size);
        data = newData;
    }

    /**
     * Remove and return a random item.
     *
     * @return the recently removed element
     */
    public T dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("The queue has no elements");
        }
        int randomIndex = RandomHelper.uniform(size);
        T valueToReturn = data[randomIndex];
        data[randomIndex] = data[--size];
        data[size] = null;
        if (size > 0 && size == data.length / 4) {
            resize(data.length / 2);
        }
        return valueToReturn;
    }

    /**
     * Return a random item (but do not remove it)
     *
     * @return a {@link T} object
     */
    public T sample() {
        if (size == 0) {
            throw new NoSuchElementException("The queue has no elements");
        }
        return data[RandomHelper.uniform(size)];
    }

    @Override
    public Iterator<T> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<T> {

        int[] positions = new int[size];
        int subSize = size;

        public RandomizedQueueIterator() {
            for (int i = 0; i < positions.length; i++) {
                positions[i] = i;
            }
            RandomHelper.shuffle(positions);
        }

        @Override
        public boolean hasNext() {
            return subSize > 0;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Cannot call this method");
            }
            return data[positions[--subSize]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Method not implemented");
        }
    }
}
