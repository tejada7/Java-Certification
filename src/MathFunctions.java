import java.util.ArrayList;
import java.util.List;

/**
 * Created by Favio on 6/6/2017.
 */
public class MathFunctions {
    public static void addToInt(int x, int amountToAdd) {
        x = x + amountToAdd;
    }

    public static void main(String[] args) {
        /*int intA = 10;
        int b = 15;
        MathFunctions.addToInt(intA,b);
        System.out.println(intA);*/
        int[] array = {6, 9, 8};
        List<Integer> list = new ArrayList<>();
        list.add(array[0]);
        list.add(array[2]);
        list.set(1, array[1]);
        list.remove(0);
        System.out.println(list);
        list.add(4);
        System.out.println(list);
    }
}
