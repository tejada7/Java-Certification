package dessignPatterns.creational.singleton;

/**
 * Advantages:
 * Keep only one instance per application
 * Improve performance
 *
 * Pitfalls:
 * Often overused
 * Difficult to unitTest
 * Not thread-safe (if not detailed attention)
 *
 */
public class DemoSingleton {

    public static void main(String[] args) {
        DBSingleton instance = DBSingleton.getInstance();
        System.out.println(instance);
    }
}
