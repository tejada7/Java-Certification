package com.javacertification.interviewquestions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

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
    @SuppressWarnings("unchecked")
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

    /**
     * Rotate a square matrix by 90 degrees. Rows become columns in descending order.
     *
     * @param matrix the original matrix.
     * @param <E>    the matrix's data type
     * @return a newly created matrix
     * @throws IllegalArgumentException if the matrix is null
     */
    public static <E> E[][] rotateMatrix(E[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("The matrix cannot be null");
        }
        int n = matrix.length;
        // Since E is a non-reifiable type there is no way the compiler can check the cast
        // The elements of the array will contain only E instances, however, at runtime the type of the array won't be
        // E[][], but rather Object[][]
        @SuppressWarnings("unchecked")
        E[][] rotatedArray = (E[][]) new Object[n][n];
        for (int i = 0; i < (n + 1) / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                rotatedArray[i][j] = matrix[n - 1 - j][i];
                rotatedArray[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                rotatedArray[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                rotatedArray[j][n - 1 - i] = matrix[i][j];
            }
        }
        return rotatedArray;
    }

    /**
     * Return the top k most frequent elements of an array.
     *
     * @param elements the array containing the elements
     * @param k        the first k elements to find
     * @param <E>      the array's data type
     * @return an array of type {@link E} containing the k most frequent elements
     * @throws IllegalArgumentException if the entry array is null
     */
    @SuppressWarnings("unchecked")
    public static <E> E[] kFrequentElements(E[] elements, int k) {
        if (elements == null) {
            throw new IllegalArgumentException("The array is null.");
        }
        Map<E, Integer> records = new HashMap<>();
        Arrays.stream(elements).forEach(e -> countElementsIntoMap(records, e));
        return records.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(k)
                .map(Map.Entry::getKey)
                .toArray(value -> (E[]) new Object[value]);
    }

    private static <E> void countElementsIntoMap(Map<E, Integer> records, E element) {
        if (records.containsKey(element)) {
            records.put(element, records.get(element) + 1);
        } else {
            records.put(element, 1);
        }
    }

    /**
     * Merge n arrays, combining them into one newly created array based on a criteria.
     *
     * @param comparator the comparator criteria
     * @param arrays     the arrays to be merged
     * @param <E>        the generic type of the arrays
     * @return a new object {@link E} arrays
     * @throws IllegalArgumentException if no arguments are provided
     */
    @SuppressWarnings("unchecked")
    public static <E> E[] mergeAndSortArrays(Comparator comparator, E[]... arrays) {
        if (arrays == null || arrays.length == 0) {
            throw new IllegalArgumentException("No arguments provided.");
        }
        return (E[]) Arrays.stream(arrays)
                .flatMap((Function<E[], Stream<?>>) Arrays::stream)
                .sorted(comparator)
                .toArray();
    }

    /**
     * Merge n arrays, combining them into one newly created array adopting the default or natural sorting order.
     *
     * @param arrays the arrays to be merged
     * @param <E>    the generic type of the arrays
     * @return a new object {@link E} arrays
     * @throws IllegalArgumentException if no arguments are provided
     */
    @SuppressWarnings("unchecked")
    public static <E> E[] mergeAndSortArrays(E[]... arrays) {
        return mergeAndSortArrays(Comparator.naturalOrder(), arrays);
    }


}
