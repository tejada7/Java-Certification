/**
 * Created by Favio on 6/6/2017.
 */
public class Egret {
    private String color;

    public Egret(String color) {
        this.color = color;
    }

    public Egret() {
        this("white");
    }

    public static void main(String[] args) {
        Egret e = new Egret();
        System.out.println("Color: " + e.color);
    }
}
