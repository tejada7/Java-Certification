import java.io.Serializable;

/**
 * Created by Favio on 24/8/2017.
 */
public class Test implements Serializable {

    public static void main(String[] args) {
        Integer a = new Integer(3);
        Integer b = new Integer(3);
        if (a == b) {
            System.out.println(true);
        }
        Object o = new Integer(102);
        byte b1 = -128;
        int a1 = b1;
        byte b2 = (byte) a1;
        System.out.println(a1 + " " + b2);
        intA inter = new intA() {
            @Override
            public void method() {

            }
        };
        float f1 = 1;
        float f2 = 1;
        double f3 = f1 + f2;
    }
}
interface intA {
    int i = 10;
    void method();
}

interface b extends intA {
    public void method();
}

class A{
    void method(){}
}
class AA extends A {
    final void method() {}
}
