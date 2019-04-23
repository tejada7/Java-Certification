package com.oca.interviewquestions;

/**
 * Contain helpful methods for arrays.
 */
public class ArrayUtil {

    private ArrayUtil() {
        throw new IllegalStateException("Constructor not allowed for a util class.");
    }

    /**
     * Sort an array of {@link E} objects that implement Comparable interface.
     *
     * @param array the elements to sort
     * @param <E>   the generic type that must implement the interface {@link Comparable} to make the comparisons
     * @throws IllegalArgumentException if the array passed as parameter is null
     * @see Comparable
     */
    public static <E extends Comparable> void bubbleSort(E[] array) {
        if (array == null) {
            throw new IllegalArgumentException("No null parameter is allowed");
        }
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i].compareTo(array[j]) > 0) {
                    swap(array, i, j);
                }
            }
        }
    }

    private static <E> void swap(E[] array, int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
