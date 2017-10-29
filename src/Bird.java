import java.io.IOException;

/**
 * Created by Favio on 14/8/2017.
 */
public abstract class Bird {
    private void fly() {
        System.out.println("Bird is flying");
    }

    public static void main(String[] args) {
        Bird bird = new Pelican();
        bird.fly();
        System.out.println(bird instanceof Bird? "bird" : "pelican");
    }
}

class Pelican extends Bird {
    public void fly() {
        System.out.println("Pelican is flying");
    }

    public void test() {
        throw new IllegalArgumentException();

    }

    class HasSoreThroatException extends Exception {
    }

    class TiredException extends RuntimeException {
    }

    interface Roar {
        void roar() throws HasSoreThroatException;
    }

    class Lion implements Roar {
        @Override
        public void roar() throws IllegalArgumentException {

        }// INSERT CODE HERE
    }
}
