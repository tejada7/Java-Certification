package com.oca.interviewquestions;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SudokuTest {

    @Test
    public void validSudokuBoard() {
        int[][] board = new int[][]{
                {4, 9, 8, 2, 6, 3, 1, 5, 7},
                {1, 3, 6, 5, 7, 8, 2, 9, 4},
                {5, 7, 2, 4, 9, 1, 6, 8, 3},
                {8, 1, 9, 3, 4, 2, 7, 6, 5},
                {6, 5, 3, 8, 1, 7, 9, 4, 2},
                {2, 4, 7, 6, 5, 9, 8, 3, 1},
                {7, 6, 1, 9, 3, 5, 4, 2, 8},
                {9, 8, 5, 1, 2, 4, 3, 7, 6},
                {3, 2, 4, 7, 8, 6, 5, 1, 9}};
        assertTrue(Sudoku.isCorrect(board));
    }

    @Test
    public void invalidDataSet() {
        int[][] board = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3, 3, 3, 3, 3},
                {0, 1, 2, 3, 4, 3, 2, 1, 0},
                {0, 1, 2, 3, 4, 3, 2, 1, 0},
                {0, 1, 2, 3, 4, 3, 2, 1, 0},
                {0, 1, 2, 3, 4, 3, 2, 1, 0},
                {0, 1, 2, 3, 4, 3, 2, 1, 0}};
        assertFalse(Sudoku.isCorrect(board));
    }

    @Test
    public void invalidRow() {
        int[][] board = new int[][]{
                {4, 4, 8, 2, 6, 3, 1, 5, 7},
                {1, 3, 6, 5, 7, 8, 2, 9, 4},
                {5, 7, 2, 4, 9, 1, 6, 8, 3},
                {8, 1, 9, 3, 4, 2, 7, 6, 5},
                {6, 5, 3, 8, 1, 7, 9, 4, 2},
                {2, 4, 7, 6, 5, 9, 8, 3, 1},
                {7, 6, 1, 9, 3, 5, 4, 2, 8},
                {9, 8, 5, 1, 2, 4, 3, 7, 6},
                {3, 2, 4, 7, 8, 6, 5, 1, 9}};
        assertFalse(Sudoku.isCorrect(board));
    }

    @Test
    public void invalidColumn() {
        int[][] board = new int[][]{
                {4, 9, 8, 2, 6, 3, 1, 5, 7},
                {1, 3, 6, 5, 7, 8, 2, 9, 4},
                {1, 7, 2, 4, 9, 5, 6, 8, 3},
                {8, 1, 9, 3, 4, 2, 7, 6, 5},
                {6, 5, 3, 8, 1, 7, 9, 4, 2},
                {2, 4, 7, 6, 5, 9, 8, 3, 1},
                {7, 6, 1, 9, 3, 5, 4, 2, 8},
                {9, 8, 5, 1, 2, 4, 3, 7, 6},
                {3, 2, 4, 7, 8, 6, 5, 1, 9}};
        assertFalse(Sudoku.isCorrect(board));
    }

    @Test
    public void invalidMatrix() {
        int[][] board = new int[][]{
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}};
        assertFalse(Sudoku.isCorrect(board));
    }

    @Test
    void generationAndCorrectnessTest() {
        final int[][] sudokuBoard = Sudoku.generateRandomBoard();
        Arrays.stream(sudokuBoard).forEach(
                array -> System.out.println(Arrays.toString(array)));
        Assert.assertTrue(Sudoku.isCorrect(sudokuBoard));
    }
}
