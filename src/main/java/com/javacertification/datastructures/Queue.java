package com.javacertification.datastructures;

/**
 * Simple generic queue implementation.
 *
 * @param <T> the generic type that the queue contains
 */
public class Queue<T> {
    private T[] data;
    private static final int DEFAULT_CAPACITY = 8;
    private int front;
    private int end;

    /**
     * Default constructor. Initializes the data array with size {@link Queue#DEFAULT_CAPACITY}.
     */
    public Queue() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Initialize the fields.
     *
     * @param size the array's length
     */
    public Queue(int size) {
        data = (T[]) new Object[size];
        front = end = -1;
    }

    /**
     * Insert an element into the queue.
     *
     * @param item the element to insert
     */
    public void enqueue(T item) {
        // First check if the queue is full
        if ((end + 1) % data.length == front) {
            resize();
        }// Otherwise check to see if any items have been added to the queue yet
        else if (size() == 0) {
            front++;
            end++;
            data[end] = item;
            // Otherwise add the item to the end of the queue
        } else {
            end++;
            data[end] = item;
        }
    }

    /**
     * Remove the last element from the queue and return its value.
     *
     * @return a {@link T} object
     */
    public T dequeue() {
        T item = null;
        // If the queue is empty we can't dequeue anything
        if (size() == 0) {
            throw new IllegalStateException("Can't dequeue because the queue is empty.");
        }
        // Otherwise if this is the last item on the queue, this needs to be reset to empty
        else if (front == end) {
            item = data[front];
            data[front] = null;
            front = -1;
            end = -1;
        }
        // Otherwise grab the front of the queue, return it and adjust the front pointer
        else {
            item = data[front];
            data[front] = null;
            front++;
        }
        return item;
    }

    /**
     * Find an element in the queue
     *
     * @param item the element to search
     * @return a <code>boolean</code> value
     */
    public boolean contains(T item) {
        if (size() == 0) {
            return false;
        }
        for (int i = front; i < end; i++) {
            if (data[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds an element in the queue and returns its value.
     *
     * @param position the element to search
     * @return a {@link T} object if found
     */
    public T get(int position) {
        final int size = size();
        if (size == 0 || position > size) {
            throw new IllegalStateException("No items in the queue or the position is greater than the queue's size");
        }
        int index = 0;
        for (int i = front; i < end; i++) {
            if (index++ == position) {
                return data[i];
            }
        }
        return null;
    }

    private void resize() {
        T[] newData = (T[]) new Object[2 * data.length];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    /**
     * Return the size field.
     *
     * @return an <code>int</code> value representing the queue's size
     */
    public int size() {
        if (front == -1 && end == -1) {
            return 0;
        } else {
            return end - front + 1;
        }
    }

}
