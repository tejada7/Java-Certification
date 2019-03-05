
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Determine the smallest prime number that is on an even position in a given vector.
 */
public class PrimeInBucle {
    public static void main(String[] args) {
        int[] vector;
        Integer result;
        try (Scanner scanner = new Scanner(/*new File(args[0])*/System.in)) {
            String[] numberTokens = scanner.nextLine().split(",");
            vector = new int[numberTokens.length];
            for (int i = 0; i < numberTokens.length; i++) {
                vector[i] = Integer.parseInt(numberTokens[i].trim());
            }
        }

        int[] cpy = Arrays.copyOf(vector, vector.length);
        result = IntStream.range(0, vector.length)
                .filter(i -> i % 2 == 0)
                .mapToObj(i -> cpy[i])
                .filter(value ->
                        IntStream.rangeClosed(1, (value / 2))
                                .filter(i -> (value % i == 0))
                                .count() == 1
                ).min(Comparator.comparing(integer -> (integer))).orElse(null);
        
        /*/for (int j = 0; j < vector.length; j++) {
            int value = vector[j];
            int counter = 0;
            if (j % 2 == 1) {
                for (int i = 1; i <= value / 2; i++) {
                    if (value % i == 0) {
                        counter++;
                    }
                }
                if (counter == 1) {
                        if (result == null || value < result) {
                            result = value;
                        }
                }
            }
        }*/
        if (result != null) {
            System.out.println(result > 0 ? result : "NULL");
        }
    }
}