package testPackage.ausy;

/**
 * Created by Favio on 25/11/2017.
 */
public class Parent {

    public int x;

    public Parent() {
        x = 5;
    }

    public static void main(String[] args) {
        Child child1 = new Child();
        Child child2 = new Child(2016);
        Child child3 = new Child("Hello");
        System.out.println(child1.x + "and" + child2.x + "and" + child3.x);
        String a = "bon", b = "jour";
        int c = 3, d = 4;
        permuter(a,b,c,d);
        System.out.println(a + ", " + ", " + b
        + ", " + c + ", " + d);
    }

    public static void permuter(String s1, String s2, int x1, int x2) {
        String tmp1 = s1;
        s1 = s2;
        s2 = tmp1;
        int tmp2 = x1; x1 = x2;
        x2 = tmp2;
    }
}

class Child extends Parent {

    public Child() {
        x++;
    }

    public Child(int i) {
        this(); x += i;
    }

    public Child(String s) {
        super();
        x--;
    }
}