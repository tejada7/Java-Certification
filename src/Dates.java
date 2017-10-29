import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Favio on 22/6/2017.
 */
public class Dates {
    public static void main(String[] args) {
        /*System.out.println(LocalDate.now());

        System.out.println(LocalTime.now());

        System.out.println(LocalDateTime.now());

        LocalDate localDate = LocalDate.of(1992, 03, 15);
        System.out.println(localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));

        Date date = new Date(2015, Calendar.MARCH, 15);
        System.out.println(date);
        int[] intA = {1, 2, 3};
        int w = "asdasd".length();*/
        /*String[] intA = { "f", "o", "o", "bar" };
        System.out.println(concat(intA));*/

        /*int[] ints = { -9, 8, 2, -5, 7 };
        int result = closestToZero(ints);

        System.out.println(result);*/
        System.out.println(add(1, 2, 3));
    }

    static String concat(String[] strings) {
        String concat = "";
        for (String string : strings) {
            if (string.length() > 0) {
                concat+=string;
            }
        }
        return concat;
    }

    static int closestToZero(int[] ints) {
        int closest = 0;
        if (null != ints ) {
            closest = (int) Math.abs(ints[0]);
            for (int i = 1; i < ints.length; i++) {
                if ((int) Math.abs(ints[i]) < closest) {
                    closest = (int) Math.abs(ints[i]);
                }
            }
        }
        return closest;
    }

    /**
     * Add up all the int elements in an array
     * @param array
     * @return the sum
     */
    static int add(int...array) {
        int sum = 0;
        for (int value: array) {
            sum+=value;
        }
        return sum;
    }

    static int method(Integer i) {
        return 0;
    }

    static int method(int i) {
        return 0;
    }
}
