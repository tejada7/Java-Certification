/**
 * Created by Favio on 24/8/2017.
 * We can compare {@link Integer} objects with == operand if and only if their value is <= 127 and their initialization
 * is made through autoboxing. e.g. Integer a = 2, Integer b = 2 -> a == b = true.
 */
public class IntegerComparison {

    public static void main(String[] args) {
        // Initialization by autoboxing
        Integer a = 127;
        Integer b = 127;
        System.out.println((a == b) ? "Equals" : "Not equals");
        // This initialization won't work
        a = new Integer(3);
        b = new Integer(3);
        System.out.println((a == b) ? "Equals" : "Not equals");
        // Out of range
        a = b = 128;
        System.out.println((a == b) ? "Equals" : "Not equals");
    }
}
