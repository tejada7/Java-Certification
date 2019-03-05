import java.awt.print.PageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static java.lang.System.out;
import static java.util.Collections.sort;

public class Miscellaneous {

    public static void main(String[] args) {
        function0();
    }

    private static void function34() {
        parseFloat("");
        List<String> hex = Arrays.asList("30", "8", "3A", "FF");
        Collections.sort(hex);
        int x = Collections.binarySearch(hex, "8");
        int y = Collections.binarySearch(hex, "3A");
        int z = Collections.binarySearch(hex, "4F");
        long l = 100_00l;
        int i = 100;
        float f = 2.02f;
        double d = 10_0.35d;
        f = l;
        d = l;
//        f = d;
        d = f;
        f = i;
//        i = f;
        i = (int) d;
        LocalDateTime ld = LocalDateTime.of(2014, 7, 31, 1, 1);
        Integer a = 128;
        Integer b = 128;
        System.out.println(new Integer(127) == new Integer(127));
        System.out.println(a == b);
        int a1 = 1;
        int a2 = 1;
        int a3 = 1;
        int a4 = 1;
    }


    private static float parseFloat(String s) {
        float f = 0.0f;      // 1
        try {
            f = Float.valueOf(s).floatValue();    // 2
//            return f ;      // 3
        } catch (NumberFormatException nfe) {
            f = Float.NaN;    // 4
            return f;     // 5
        } finally {
            return f;     // 6
        }
//        return f ;    // 7
    }

    static class Super {
        static String String = "String";

        static {
            out.println("init Super");
        }

        {
            out.println("init Super instance");
        }
    }

    static class Sub extends Super {
        static {
            out.println("init sub");
        }

        static String String1 = "String1";
    }

    private static void function33() {
        A a = new A();
        B b = new B();
        a = (B) (I) b;
        I i = (C) a;
    }

    interface I {
    }

    static class A implements I {
    }

    static class B extends A {
    }

    static class C extends B {
    }

    /**
     * Example of taking the most specific object.
     */
    private static void function32() {
        myOverloadedMethod(null);
    }

    private static void function31() {
        int i = 5;
        float f = 5.4f;
        out.println(i == f ? "equals" : "non equals");
        out.println(Boolean.parseBoolean(" truE ")); // the String is not trimmed
    }

    static void myOverloadedMethod(Object o) {
        out.println("object");
    }

    static void myOverloadedMethod(RuntimeException o) {
        out.println("runtime");
    }

    static void myOverloadedMethod(NullPointerException o) {
        out.println("NPE");
    }

    static void myOverloadedMethod(Exception o) {
        out.println("exception");
    }

    private static void function30() {
        int i;
        LOOP:
        for (i = 0; i < 5; i++) {
            switch (i++) {
                case '0':
                    out.println("A");
                case 1:
                    out.println("B");
                    break LOOP;
                case 2:
                    out.println("C");
                    break;
                case 3:
                    out.println("D");
                    break;
                case 4:
                    out.println("E");
                case 'E':
                    out.println("F");
            }
        }
    }

    private static void function29() {
        int i = 4;
        int[][][] array = new int[i][i = 3][i];
        out.println(array.length + ", " + array[0].length + ", " + array[0][0].length);
        out.println("hello world".compareTo("Hello world"));
        int[] a = {1, 2, 3, 4};
        int[] b = {2, 3, 1, 0};
        out.println(a[(a = b)[3]]);
    }

    interface Account {
        public default String getId() {
            return "0000";
        }
    }

    interface PremiumAccount extends Account {
        // Illegal
//        public String getId();
    }

    static class KK implements PremiumAccount {

        public String getId1() {
            return PremiumAccount.super.getId();
        }
    }

    private static void function28() {
        String s = "newworld";
        int i = s.length();
        while (!s.isEmpty()) {
            i++;
            s = s.substring(1);
        }
        out.println(i);
    }

    /**
     * HH = 24hour format, whereas hh = 12hour format.
     */
    private static void function27() {
        LocalDate depDate = LocalDate.of(2015, 12, 12);
        LocalTime depTime = LocalTime.of(10, 20);
        out.println("depTime: " + depTime);
        LocalDateTime departure = LocalDateTime.of(depDate, depTime);
        LocalDateTime arrival = departure.plusHours(8).plusMinutes(30);
        out.println(arrival.format(
                DateTimeFormatter.ofPattern("yyyy mm dd HH:MM")));
    }

    /**
     * '==' work only on literal Integers, Longs, Shorts, Bytes and Characters whose value 'v' is -128 <= v <=127 and
     * whether its initialization is made by direct literal assignment and not by new Object...
     * For example:
     * Integer i1 = 127 and Integer i2 = 127 (attention!!! new Integer(127) will not work!)
     */
    private static void function26() {
        Long l1 = -129L;
        Long l2 = -129L;
        out.println(l1 == l2);
        Double d1 = 12d;
        Double d2 = 12d;
        out.println(d1 == d2);
        Character c1 = 127;
        Character c2 = 127;
        out.println(c1 == c2);
    }

    /**
     * When narrowing int to byte:
     * For positive integers: the original value must be reduced by 256, otherwise added.
     */
    private static void function25() {
        int i = -129;
        out.println((byte) i);
    }

    /*
     * dd MM yyyy
     * d M yyy
     */
    private static void function24() {
        String pattern = "d M yyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate date = LocalDate.parse("24 04 2019", formatter);
        out.println(formatter.format(date));
    }

    private static void function23() {
        Holder h1 = new Holder();
        h1.length++;
        h1.width++;
        Holder h2 = new Holder();
        out.print(h2.width + ", ");
        out.print(h2.length);
    }

    static class Holder {
        float length = 100f;
        static float width = 12.30F;

        static {
            width = 23.00F;
        }

        static {
            width = 25.00F;
        }
    }

    private static void function22() {
        StringBuilder sb = new StringBuilder("Best phone cameras!");
        out.println(sb.replace(1, 5, "Worst"));
        Predicate<Long> s = (x) -> {
            return true;
        };
        String s1 = "Java"; // Watch the difference when using final in s1 and s2
        String s2 = "Rocks";
        String s3 = "JavaRocks";
        out.println(s3 == s1 + s2);
    }

    private static void function21() {
        try {
            throw new Exception("abcde");
        } catch (Exception e) {
            /*System.out.println(e.toString()); //the {@linkplain Class#getName() name} (a colon and a space)
            System.out.println("___________________________________");
            System.out.println(e.getMessage()); // only the message (if present)*/
            /*System.out.println("___________________________________");*/
            e.printStackTrace();
            out.println(e);
        }
    }

    private static void function20() {
        String s1 = new String("abc");
        String s2 = new String("abc");
        out.println(s1 == s2);  //would return false  by rule #4
        out.println("abc" == "a" + "bc");  //would return true by rules #2 and #3
        out.println("abc" == s1);  //would return false  by rules #1,2 and #4
        out.println("abc" == s1.intern());  //would return true  by rules #1,2,4 and #6
        out.println(s1 == s2.intern());      //wound return false by rules #1,4, and #6
        String hole = "123";
        out.println(hole == "1" + "2" + "3");
    }

    private static void function19() {
        Integer val1 = new Integer(5);
        int val2 = 9;
        testInts(val1++, ++val2);
        out.println(val1 + " " + val2); // 11 10
    }

    private static void testInts(Integer val1, int val2) {
        val1 = val2++;
        val1++; // val1 = 11
    }

    private static void function18() {
        int[][] array = new int[][]{{1, 2, 3}, {4, 5, 6}};
        out.println(array.length);
        for (int i = 0; i < array.length; i++) {
            out.println();
            for (int j = 0; j < array[i].length; j++) {
                out.print(array[i][j] + " ");
            }
        }
    }

    /**
     * Integer.MIN_VALUE = -Integer.MIN_VALUE because 2 compliment.
     */
    private static void function17() {
        Integer min = Integer.MIN_VALUE;
        Integer max = Integer.MAX_VALUE;
        out.println("min es: " + min);
        out.println("max es: " + max);
        out.println("-min es: " + (-1 * (min)));
        out.println("max - min es: " + (max + min));
    }

    private static void function16() {
        short s = Short.MAX_VALUE;
        char c = (char) s;
        Integer aa = 1;
        c += aa;
        String str = "";
        boolean b = false;
        out.println(c == Short.MAX_VALUE);
        char[] cc = new char[0];
        out.println(Arrays.toString(cc) + " " + cc.length);
    }

    private static void function15() {
        StringBuilder sb1 = new StringBuilder("sb1");
        StringBuilder sb2 = sb1.append("a");
        if (sb1.equals(sb2)) {
            out.println("1");
        }
        StringBuilder sb3 = sb1.append("y");
        if (sb3.equals(sb2)) {
            out.println("2");
        }
    }

    private static void function14() {
        int i = 3;
        int j = 7;
        if (i > j) {
            out.println("1 ");
        } else if (i + 4 < j) {
            out.println("2 ");
        } else if (j > 5) {
            out.println("3 ");
        }
    }

    private static void function13() {
        Miscellaneous miscelaneaus = new Miscellaneous();
        miscelaneaus.go(x -> true);
        miscelaneaus.go(x -> new Boolean(true));
        miscelaneaus.go(x -> {
            return 6 < 4;
        });
    }

    void go(Predicate<Miscellaneous> predicate) {
        Miscellaneous miscelaneaus = new Miscellaneous();
        out.println(predicate.test(miscelaneaus));
    }


    private static void function12() {
        Integer a = 127;
        Integer b = 127;
        out.println(a == b);
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(true);
        out.println(list);
    }

    private static void function11() {
        boolean b1 = false, b2;
        int x = 2, y = 5;
        b1 = 2 - 12 / 4 > 5 + -7 && b1 != y++ > 5 == 7 % 4 > ++x | b1 == true;
        b2 = (2 - 12 / 4 > 5 + -7) && (b1 != y++ > 5) == (7 % 4 > ++x) | (b1 == true);
        out.println(b1 + " " + b2);
    }

    private static void function10() {
        Mother c = new Concrete();
        Mother c1 = new Mother();
        Concrete c2 = new Concrete();
        c = c1 = c2 = null;
        c.method();
        c1.method();
        c2.method();
        //Mother's static init
        //Child's static init
        //Mother's instance init
        //Mother's constructor
        //Child's instance init
        //Child's constructor

        //From mother
        //From mother
        //From child
    }

    static class Mother {
        protected int x;

        static {
            out.println("Mother's static init");
        }

        {
            out.println("Mother's instance init");
        }

        Mother() {
            out.println("Mother's constructor");
        }

        static void method() {
            out.println("\n***Method from mother!***\n");
        }
    }

    static class Concrete extends Mother {

        static {
            out.println("Child's static init");
        }

        {
            out.println("Child's instance init");
        }

        Concrete() {
            out.println("Child's constructor\n");
        }

        static void method() {
            out.println("\n***Method from child!***\n");
        }
    }

    private static void function9() {
        PageFormat path = new PageFormat();
        path.setOrientation(3);
        try {
            out.println("Normal flow.");
            throw new Exception();
        } catch (Exception e) {
            out.println("catch1");
            out.println("catch2");
        } finally {
            out.println("finally");
        }
        out.println("Reached the end.");
    }

    private static void function8() {
        short s = 544;
        byte b = 1;
        int i = s;
        long l = 1;
        float f = 1.4f;
        double d = 1.4;
        char c = '0';
        double d1 = c;
        // Not valid
        /*float f = s;
        double d = s;
        byte b = (byte) i;*/
    }

    private static void function7() {
        List list = new ArrayList<Integer>();
        list.add(new Integer(5));

        ArrayList<Integer> clone = (ArrayList<Integer>) ((ArrayList) list).clone();
        Integer i1 = clone.get(0);
        i1++;
        out.println(list);
        out.println(clone);
        int[] arr = new int[]{1, 2, 3};
        Period.ofYears(-3000);
    }

    private static void function6() {
        StringBuilder builder = new StringBuilder(".....");
        CharSequence charSequence = "Una cadena de caracteres.";
        builder.append(charSequence, 4, 10);
        builder.insert(11, " blablabla ");
        builder.insert(22, new char[]{'a', 'e', 'i', 'o', 'u'}, 0, 4);
        out.println(builder + " su length es: " + builder.length());
        out.println(builder.delete(0, 5));
    }

    private static void function5() {
        List<Character> list = new ArrayList<>();
        list.add(0, 'V');
        list.add('T');
        list.add(1, 'E');
        list.add(3, 'O');

        if (list.contains('O')) {
            list.remove('O');
        }
        for (char ch : list) {
            out.print(ch);
        }
    }

    public static String function4() {
        String result = "";
        String v = null;
        try {
            try {
                result += "before";
                v.length();
                result += "after";
            } catch (NullPointerException e) {
                result += "catch";
                throw new RuntimeException();
            } finally {
                result += "finally";
                throw new Exception();
            }
        } catch (Exception e) {
            result += "done";
        }
        return result;
    }

    synchronized final static strictfp void function3() {
        String[] names = {"Tom", "Richard", "Harry"};
        List<String> list = Arrays.asList(names);
        list.set(0, "Sue");
        sort(list);
        out.println(names[0]);
    }

    static void function2() {
        int count = 0;
        int x = 3;
        while (count++ < 3) {
            int y = (1 + 2 * count) % 3;
            switch (y) {
                default:
                case 0:
                    x -= 1;
                    break;
                case 1:
                    x += 5;
            }
        }
        out.println(x);
    }

    static void function1() {
        int x = 5, j = 0;
        OUTER:
        for (int i = 0; i < 3; ) {
            INNER:
            do {
                i++;
                x++;
                if (x > 10) {
                    break INNER;
                }
                x += 4;
                j++;
            } while (j <= 2);
        }
        out.println(x);
    }

    private static void function0() {
        Object o1[] = new Object[1];
        Object o2[] = o1;
        o1 = null;
        out.println(o1 + "\n " + o2);
    }
}
