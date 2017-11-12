package editx;

public class Easy {

    public static void main(String[] args) {
        /*Double i = 127d;
        Double j = 127d;
        if (i == j) {
            System.out.println("Open");
        } else {
            System.out.println("Hello");
        }
        i = new Double(1);
        j = new Double(1);
        if (i == j) {
            System.out.println("Groupe");
        } else {
            System.out.println("Challenge");
        }*/

        try {
            Foo foo = new Foo();
            throw new Exception("Error");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Finally");
        }
    }
}

class Foo implements AutoCloseable {

    @Override
    public void close() throws Exception {
        System.out.println("Closed");
    }
}