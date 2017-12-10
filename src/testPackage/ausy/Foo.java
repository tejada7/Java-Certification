package testPackage.ausy;

/**
 * Created by Favio on 25/11/2017.
 */
public class Foo {
    public int f() {
        return 5;
    }

    public static int g() {
        return 6;
    }

    public static void main(String[] args) {
        Bar b = new Bar();
        Foo m = b;
        System.out.println(m.f() * m.g());
    }
}

class Bar extends Foo {
    public int f() {
        return 2;
    }

    public static int g() {
        return 4;
    }
}