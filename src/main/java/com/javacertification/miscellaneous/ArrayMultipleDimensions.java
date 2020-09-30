package com.javacertification.miscellaneous;

/**
 * Created by Favio on 20/8/2017.
 * In Java, arrays' rows vary in length.
 */
public class ArrayMultipleDimensions {
    public static void main(String[] args) {
        int array[][] = {
                {1, 13},
                {1, 2, 5, 4},
                {2, 7, 2}};
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print("(" + i + ", " + j + "), value: " + array[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
