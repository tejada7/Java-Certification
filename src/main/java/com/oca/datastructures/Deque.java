package com.oca.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A double-ended queue or deque (pronounced “deck”) is a generalization of a stack and a queue that
 * supports adding and removing items from either the front or the back of the data structure.
 *
 * @param <T> the generic type
 */
public class Deque<T> implements Iterable<T> {

    private Node first;
    private Node last;
    private int elementsCount;

    /**
     * Is the deque empty?
     *
     * @return a <code>boolean</code> value
     */
    public boolean isEmpty() {
        return elementsCount == 0;
    }

    /**
     * Return the number of items on the deque.
     *
     * @return an <code>int</code> value
     */
    public int size() {
        return elementsCount;
    }

    /**
     * Add one item to the front.
     *
     * @param item the element to add
     */
    public void addFirst(T item) {
        throwExceptionIfNullInput(item);
        Node newNode = initNodeAndIncrementCounter(item);
        newNode.next = first;
        if (first == null && last == null) {
            first = last = newNode;
        } else {
            first = newNode;
        }
    }

    /**
     * Add one item to the end.
     *
     * @param item the element to add
     */
    public void addLast(T item) {
        throwExceptionIfNullInput(item);
        Node newNode = initNodeAndIncrementCounter(item);
        if (last == null) {
            first = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
    }

    private Node initNodeAndIncrementCounter(T item) {
        elementsCount++;
        return new Node(item);
    }

    private void throwExceptionIfNullInput(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null value.");
        }
    }

    /**
     * Remove and return the item from the front.
     *
     * @return the element being removed
     */
    public T removeFirst() {
        throwExceptionIfEmpty();
        Node itemToDelete = first;
        elementsCount--;
        first = first.next;
        if (itemToDelete == last) {
            last = null;
        }
        itemToDelete.next = null;
        return itemToDelete.value;
    }

    /**
     * Remove and return the item from the end.
     *
     * @return the element being removed
     */
    public T removeLast() {
        throwExceptionIfEmpty();
        if (first == last) {
            T itemToRemove = first.value;
            first = last = null;
            elementsCount--;
            return itemToRemove;
        }
        Node copy = first;
        int counter = 1;
        while (counter < elementsCount - 1) {
            copy = copy.next;
            counter++;
        }
        T itemToRemove = last.value;
        last = copy;
        last.next = null;
        elementsCount--;
        return itemToRemove;
    }

    private void throwExceptionIfEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException("The deque is empty.");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    private class Node {

        private final T value;
        private Node next;

        private Node(T value) {
            this.value = value;
        }
    }

    private class DequeIterator implements Iterator<T> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Cannot call this method.");
            }
            T t = current.value;
            current = current.next;
            return t;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Method not implemented.");
        }
    }
}
