package com.javacertification.miscellaneous;

/**
 * When calling a overridden method, at runtime the JRE will detect it based on the object initialized, and not based on the reference.
 */
public class PolyformicMethod {
    public static void main(String[] args) {
        Base2 b = new Base3();
        System.out.println(b.getValue());// base3 2
        System.out.println(new Base3().getValue());// base3 2
        System.out.println(new Base2().getValue());// base2 1
        System.out.println(new Base().getValue()); //base 0
    }
}

class Base {
    int field = 0;
    public Object getValue() {
        return "base" + field;
    }
}

class Base2 extends Base {
    int field = 1;
    public String getValue() {
        return "base2" + field;
    }
}

class Base3 extends Base2 {
    int field = 2;
    public String getValue() {
        return "base3" + field;
    }
}
