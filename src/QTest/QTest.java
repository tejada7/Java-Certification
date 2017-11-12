package QTest;

/**
 * Created by Favio on 1/11/2017.
 */
class A {
    private int alt = 1;
    public void inc() {alt++;}
    public String toString() {
        return String.valueOf(alt);
    }

}
class B extends A {
    public void what() {
        System.out.println("B");
    }
}

public class QTest {
    public static void main(String[] args) {
        int i = 1;
        A a = new A();
        String s = "Hi";
        meth(i,a,s);
        System.out.println(String.valueOf(i) + " " + a + " " + s);
    }

    private static void meth(int i, A a, String s) {
        i+=1;
        a.inc();
        s = s.toUpperCase();
    }
}
