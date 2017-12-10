package testPackage.ausy;

/**
 * Created by Favio on 25/11/2017.
 */
public class Puzzle {
    public int test() {
        int i = 1;
        try {
            return i;
        } finally {
            i = 2;
        }
    }

    public static void main(String[] args) {
        Puzzle p = new Puzzle();
        System.out.println(p.test());
    }
}
