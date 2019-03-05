/**
 * Given an array of size n, this class sums up the non-zero values recursively.
 */
public class Recursion {

    public static int sumUp(int size, int... array) {
        if (size == 0) {
            return array[size];
        } else {
            return array[--size] + sumUp(size, array);
        }
    }

    public static void main(String[] args) {
        int array[] = {0, 1, 2, 3, 4, 5};
        System.out.println("Result: " + sumUp(array.length, array));
    }
}
