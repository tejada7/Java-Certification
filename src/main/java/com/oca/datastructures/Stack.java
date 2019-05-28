package com.oca.datastructures;

/**
 * Simple generic stack implementation.
 *
 * @param <T> the generic type that the stack contains
 */
public class Stack<T> {
    private T[] data;
    private static final int DEFAULT_CAPACITY = 8;
    private int size;

    public int getSize() {
        return size;
    }

    /**
     * Default constructor, it initializes the data array with the default capacity.
     * <p>
     * Since T is a non-reifiable type there is no way the compiler can check the cast
     * The elements of the array will contain only T instances, however, at runtime the type of the array won't be
     * T[], but rather Object[].
     * </p>
     */
    @SuppressWarnings("unchecked")
    public Stack() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void resize() {
        @SuppressWarnings("unchecked")
        T[] newArray = (T[]) new Object[size * 2];
        System.arraycopy(data, 0, newArray, 0, data.length);
        data = newArray;
    }

    /**
     * Insert an element into the stack.
     *
     * @param element the element to be inserted
     */
    public void push(T element) {
        if (size == data.length) {
            resize();
        }
        data[size++] = element;
    }

    /**
     * Remove the first element of the stack
     *
     * @return an object of type {@link T}
     */
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("No item is present in the stack.");
        }
        return data[--size];
    }

    /**
     * Check whether the stack is empty.
     *
     * @return a <code>boolean<code/> value, based on the condition
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Find the first occurrence to an element.
     *
     * @param element the element to search
     * @return a <code>boolean</code> value
     */
    public boolean contains(T element) {
        for (T item : data) {
            if (element.equals(item)) {
                return true;
            }
        }
        return false;
    }
}
