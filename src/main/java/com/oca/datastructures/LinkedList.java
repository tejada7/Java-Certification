package com.oca.datastructures;

import java.util.NoSuchElementException;
import java.util.StringJoiner;

/**
 * Simple generic linked list implementation.
 *
 * @param <T> the generic type of this class
 */
public class LinkedList<T> {
    private Node first;
    private Node last;
    private int nodeCount;

    public int size() {
        return nodeCount;
    }

    /**
     * Add one item to the list.
     *
     * @param item the item to be added
     * @throws IllegalStateException if the list is empty
     */
    public void add(T item) {
        throwExceptionIfNullInput(item);
        Node newNode = new Node(item);
        if (last == null) {
            first = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        nodeCount++;
    }

    /**
     * Insert an item at a given valid position k so that 0 <= k <= size + 1.
     * {0 1 -> null}
     *
     * @param item     the item to be inserted
     * @param position the position of the list where to insert
     * @throws IndexOutOfBoundsException if the position is empty
     */
    public void insert(T item, int position) {
        throwExceptionIfOutOfBoundsForInserting(position);
        Node nodeToInsert = new Node(item);
        if (position == 0) {
            nodeToInsert.next = first;
            first = nodeToInsert;
        } else if (position == size()) {
            last.next = nodeToInsert;
            last = nodeToInsert;
        } else {
            int currentPosition = 0;
            Node left = first;
            Node right = first.next;
            while (currentPosition++ < position - 1) {
                left = left.next;
                right = right.next;
            }
            left.next = nodeToInsert;
            nodeToInsert.next = right;
        }
        nodeCount++;
    }

    private void throwExceptionIfOutOfBoundsForInserting(int position) {
        if (position < 0 || position > size()) {
            throw new IndexOutOfBoundsException("The position " + position + " is invalid.");
        }
    }

    private void throwExceptionIfOutOfBoundsForRemoving(int position) {
        if (position < 0 || position > size() - 1) {
            throw new IndexOutOfBoundsException("The position " + position + " is invalid.");
        }
    }

    private void throwExceptionIfNullInput(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null value.");
        }
    }

    /**
     * Remove and return an element at a given position.
     *
     * @param position the position
     * @return a {@link T} object
     * @throws IndexOutOfBoundsException if the position is invalid
     */
    public T removeAt(int position) {
        throwExceptionIfOutOfBoundsForRemoving(position);
        Node nodeToRemove = null;
        if (position == 0) {
            nodeToRemove = first;
            first = first.next;
            nodeToRemove.next = null;
        } else {
            int currentPosition = 0;
            Node iterableNode = first;
            while (currentPosition++ < position - 1) {
                iterableNode = iterableNode.next;
            }
            nodeToRemove = iterableNode.next;
            if (nodeToRemove == last) {
                last = iterableNode;
            }
            iterableNode.next = nodeToRemove.next;
            nodeToRemove.next = null;
        }
        nodeCount--;
        return nodeToRemove.item;
    }

    /**
     * Remove and return the list's first element.
     *
     * @return a {@link T} object
     * @throws IllegalStateException if the list is empty
     */
    public T removeFirst() {
        throwExceptionIfEmpty();
        Node itemToRemove = first;
        if (itemToRemove == last) {
            last = null;
        }
        first = first.next;
        itemToRemove.next = null;
        nodeCount--;
        return itemToRemove.item;
    }

    /**
     * Return the element at the given position.
     *
     * @param position the position
     * @return a {@link T} object
     * @throws IndexOutOfBoundsException if the position is invalid
     * @throws IllegalStateException if the list is empty
     */
    public T get(int position) {
        throwExceptionIfOutOfBoundsForRemoving(position);
        throwExceptionIfEmpty();
        Node iterableNode = first;
        int c = 0;
        while (c++ < position) {
            iterableNode = iterableNode.next;
        }
        return iterableNode.item;
    }

    /**
     * Return the position of the first found item
     *
     * @param item the item to search
     * @return a <code>int</code> value
     * @throws IllegalStateException if the list is empty
     * @throws NoSuchElementException if the value is not found
     */
    public int find(T item) {
        throwExceptionIfEmpty();
        Node iterableNode = first;
        int position = 0;
        while (iterableNode != null) {
            if (iterableNode.item.equals(item)) {
                return position;
            }
            iterableNode = iterableNode.next;
            position++;
        }
        throw new NoSuchElementException("The value " + item + " has not been found in the list.");
    }

    private void throwExceptionIfEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException("The list is empty.");
        }
    }

    private boolean isEmpty() {
        return nodeCount == 0;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("->", "[", "]");
        Node current = first;
        while (current != null) {
            joiner.add(current.item.toString());
            current = current.next;
        }
        return joiner.toString();
    }

    private class Node {
        private T item;
        private Node next;

        public Node(T item) {
            this.item = item;
        }
    }
}
