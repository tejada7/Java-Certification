/**
 * Given an array of size n, this class sums up the non-zero values recursively.
 */
public class Recusrion {

    public static int sumUp(int size, int... array) {
        if (size == 0) {
            return array[size];
        } else {
            return array[size - 1] + sumUp(--size, array);
        }
    }

    public static void main(String[] args) {
        /*int array[] = {0, 1, 2, 3, 4, 5};
        System.out.println("Result: " + sumUp(array.length, array));*/
        new Recusrion();
    }

    static {
        add(2);
    }

    static void add(int num) {
        System.out.print(num + " ");
    }

    // Constructor
    Recusrion() {
        add(5);
    }

    static {
        add(4);
    }

    {
        add(6);
    }

    static {
        new Recusrion();
    }

    {
        add(8);
    }
}
/*ArrayList.size()
* array[].length
* */
