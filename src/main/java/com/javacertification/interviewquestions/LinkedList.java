package com.javacertification.interviewquestions;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Basic generic implementation of a simple linked list with a self-reference field.
 *
 * @param <T> the type of Object that the list contains
 */
public class LinkedList<T> {

    private Node head;

    private class Node {
        final T value;
        Node next;

        private Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    /**
     * Adds a {@link T} element to the list.
     *
     * @param value the value of the element
     */
    public void add(T value) {
        Node node = new Node(value, null);
        if (head == null) {
            head = node;
        } else {
            Node last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = node;
        }
    }

    /**
     * Add an arrays of {@link T} elements to the linked list.
     *
     * @param values an array containing the values to add
     */
    @SafeVarargs
    public final void add(T... values) {
        if (values != null) {
            Arrays.stream(values).forEach(this::add);
        }
    }

    /**
     * Reverses the list.
     */
    public void reverse() {
        if (head == null) {
            return;
        }
        Node aux = head;
        Node aux2;
        while (aux.next != null) {
            aux2 = aux.next;
            aux.next = aux2.next;
            aux2.next = head;
            head = aux2;
        }
    }

    /**
     * Removes an existing node within a list.
     *
     * @param value the node's value to be deleted
     */
    public void remove(T value) {
        if (head != null) {
            if (head.value.equals(value)) {
                head = head.next;
            } else {
                Node aux = head;
                while (aux.next != null) {
                    if (aux.next.value.equals(value)) {
                        aux.next = aux.next.next;
                        return;
                    }
                    aux = aux.next;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("->", "[", "]");
        Node last = head;
        while (last != null) {
            joiner.add(last.value.toString());
            last = last.next;
        }
        return joiner.toString();
    }
}
