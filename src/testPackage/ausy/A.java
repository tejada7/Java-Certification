package testPackage.ausy;

import java.sql.SQLException;

/**
 * Created by Favio on 25/11/2017.
 */
public class A {
    int a;
//    public A(int a) {
//        this.a = a;
//    }
//
//    public A() {
//
//    }
    public void quiSuisJe() {
        System.out.println("A");
    }

    public void m() {
        System.out.print("A");
    }
}

class B extends A {
    int b;
    public void test() {
        System.err.println("Test");
    }

    public void quiSuisJe() {
        System.out.println("B");
    }

    public void m() {
        System.out.print("B");
        super.m();
    }

//    public B(int b) {
//        super(b);
//        this.b = 2 * b;
//        this.a = b;
//    }

    public static void main(String[] args) {
//        A pa = new B();
//        pa.quiSuisJe();

//        B b = new B();
//        A a = new A();
//        System.out.println(b instanceof A);
//        System.out.println(b instanceof B);
//        System.out.println(a instanceof A);
//        System.out.println(a instanceof B);

        (new B()).m();
    }

    private static void call() throws NumberFormatException {

    }
}