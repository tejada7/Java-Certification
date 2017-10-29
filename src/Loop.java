import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Favio on 17/8/2017.
 */
public class Loop {
    public static void main(String[] args) {
        for (int i = 0; i < 10;) {
            i = ++i;
            System.out.println(i);
        }
        boolean flag = false;
        if (flag = true) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
        ArrayList<String> a = new ArrayList<>();
        a.add("1");
        a.add("2");
        ArrayList<String> b = new ArrayList<>();
        b.add("1");
        b.add("2");
        System.out.println(a.equals(b) ? "YES" : "NO");
        int array[][] = {{1, 2}, {3, 4}};
        System.out.println(method(true, true, true));
    }

    private static int method(boolean b, boolean...b1) {
        return b1.length;
    }
}
