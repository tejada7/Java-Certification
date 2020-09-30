package com.javacertification.interviewquestions;

import com.javacertification.miscellaneous.RandomHelper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A Sudoku board is built from 9x9 squares, where each square contains a digit from 1 to 9.
 * This class allows to create random Sudoku boards as wells as check the compliance with the rules to determine
 * whether a given matrix is a Sudoku.
 */
public class Sudoku {

    private Sudoku() {
        throw new IllegalStateException("This class should not be initialized.");
    }

    /**
     * Generate a Random sudoku matrix.
     *
     * @return an array of <code>byte</code> values
     */
    public static int[][] generateRandomBoard() {
        int[][] board = new int[9][9];
        int[] initialValues = IntStream.rangeClosed(1, 9).toArray();
        RandomHelper.shuffle(initialValues);
        for (int row = 0; row < 3; row++) {
            populateRow(board, row, initialValues);
        }
        return board;
    }

    private static void populateRow(int[][] matrix, int row, int[] shuffledArray) {
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 9; i++) {
                int r = i / 3 + (3 * row);
                int columnOrder = 3 * j;
                int c = i % 3 + columnOrder;
                int value = (i + columnOrder) % 9 + 1;
                value = shuffledArray[value - 1];
                if (row > 0) {
                    int rowUpperBandToBeCopied = r - 3 * row;
                    int columnUpperBandToBeCopied = (c + row) % 9;
                    value = matrix[rowUpperBandToBeCopied][columnUpperBandToBeCopied];
                }
                matrix[r][c] = value;
            }
        }
    }

    /**
     * Verifies whether a given matrix complies with the Sudoku's rules:
     * The fist rule is that in each row and each column, a given number can appear only once.
     * The second rule is that inside each smaller 3x3 matrix, digits cannot be repeated.
     *
     * @param board the array of arrays containing the values
     * @return a <code>boolean</code> value
     */
    public static boolean isCorrect(int[][] board) {
        for (int i = 0; i < 9; i++) {
            int[] row = board[i];
            Set<Integer> rowAsSet = Arrays.stream(row).boxed().collect(Collectors.toSet());
            //check each row
            if (!contains9Numbers(rowAsSet)) {
                return false;
            }
            Set<Integer> columnAsSet = new HashSet<>();
            Set<Integer> matrixAsSet = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                columnAsSet.add(board[j][i]);
                matrixAsSet.add(board[(i / 3) * 3 + j / 3][i * 3 % 9 + j % 3]);
            }
            //check each column
            if (!contains9Numbers(columnAsSet)) {
                return false;
            }
            //check each 3x3 matrix
            if (!contains9Numbers(matrixAsSet)) {
                return false;
            }
        }
        return true;
    }

    private static boolean contains9Numbers(Set<Integer> set) {
        return set.size() == 9;
    }
}
