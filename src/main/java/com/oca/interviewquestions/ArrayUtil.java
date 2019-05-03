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
}
