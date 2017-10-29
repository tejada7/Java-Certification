public class TestClass {
    public static void main(String[] args) {
        Base2 b = new Base3();
        System.out.println(new Base3().getValue());

        System.out.println(new Base3().field);
    }
}

class Base {
    int field = 0;
    public Object getValue() {
        return "base";
    } //1
}

class Base2 extends Base {
    int field = 1;
    public String getValue() {
        return "base2";
    } //2 }
}

class Base3 extends Base2 {
    int field = 2;
    public String getValue() {
        return "base3";
    } //1
}
