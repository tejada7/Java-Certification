/**
 * Created by Favio on 11/9/2017.
 */
public class StringComparison {
    public static void main(String[] args) {
        String hello = "Hello", lo = "lo";
        System.out.print((Other.hello == hello) + " ");
        System.out.print((hello == ("Hel" + "lo")) + " ");
        System.out.print((hello == ("Hel" + lo)) + " ");
        System.out.println(hello == ("Hel" + lo).intern());
    }
}

class Other {
    static String hello = "Hello";
}
