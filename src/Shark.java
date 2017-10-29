import java.time.*;
import java.util.Arrays;

/**
 * Created by Favio on 22/8/2017.
 */
public class Shark {
    static int num;
    static Water water;

    public static void main(String[] args) {
        String s1 = water.toString();
//        String s1 = num.to
    }
}

class Water {
    public String toString1() {
        int x = 10 % 2;
        int y = 3 / 5  + ++x;
        int z = 4 * x;
        return "";
    }
    boolean test(int[] ints, int k){
        int array[] = new  int[1000000];
        for (int i = 0; i< array.length; i++) {
            array[i] = -100 + (int) (Math.random() * 100);
        }
        Arrays.sort(array);
        System.out.println(Month.APRIL + " " + LocalDate.now());
        return Arrays.binarySearch( ints, k) > 0? true : false;
    }

}