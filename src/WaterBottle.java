/**
 * Created by Favio on 13/6/2017.
 */
public class WaterBottle {
    private String brand;
    private boolean empty;

    public static void main(String[] args) {
        String string;
        WaterBottle waterBottle = new WaterBottle();
        System.out.println("emptyBottle: " + waterBottle.empty);
        System.out.println("brand: " + waterBottle.brand);
        double var = 1.02;
        System.out.println(var);
        float a = 62.0f;
        System.out.println(a);
        long x = 5;
        long y = (x = 3);
    }
}
