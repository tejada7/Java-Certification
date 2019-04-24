package com.oca.interviewquestions;

import static java.lang.System.out;

/**
 * Contain methods to print crazy stuff onto the console.
 */
public class LogicSequencesGenerator {

    /**
     * The input argument indicates the maximum number of stars printed in a row. The
     * program needs to print one star in one row, two stars in the second row,
     * etc. until a maximum is reached. After that, it decreases the number of stars
     * from maximum to one, as presented below:
     * *
     * **
     * ***
     * ****
     * ***
     * **
     * *
     *
     * @param row the maximum number of stars printed in a row
     */
    private static void printStars(int row) {
        for (int i = 0; i < row; i++) {
            out.print("*");
        }
        out.println();
    }

    public static void main(String[] args) {
        try {
            int n = Integer.parseInt(args[0]);
            if (n <= 0) {
                return;
            }
            int row = 1;
            while (row < n) {
                printStars(row);
                row++;
            }

            while (row > 0) {
                printStars(row);
                row--;
            }
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            out.println("No argument was sent");
        }
    }
}
