import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.function.Predicate;

/**
 * Created by Favio on 15/8/2017.
 */
class Parent {
    String name = this.toString();
    static int field;
    public Parent(String name) {
        this.name = name;
    }

}

class Child extends Parent {
    private int age;

    public Child(int age) {
        super("");
        this.age = age;
    }

    void method() {

    }

    public static void main(String[] args) throws Error, ClassNotFoundException {
        String g = null;
        g+="a";
        g.intern();
        System.out.println(g);
    }
}
