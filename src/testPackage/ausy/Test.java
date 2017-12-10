package testPackage.ausy;

/**
 * Created by Favio on 25/11/2017.
 */
public class Test {
    public static String s = "0";
    public void inc() {
        s += 1;//011
    }

    public void print() {
        System.out.print(s);
    }

    public static void main(String[] args) {
        Test t1 = new Test(), t2 = new Test();
        t1.inc(); t2.inc();
        t1.print(); t2.print();
    }
}
