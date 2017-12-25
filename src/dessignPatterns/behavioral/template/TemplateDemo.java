package dessignPatterns.behavioral.template;

/**
 * Advantages:
 * Defined an algorithm that allows subclasses to redefine parts of the algorithms without changing its structure
 * Code reuse
 *
 * Pitfalls:
 * Restrict access
 * Confusing class hierarchy
 * Difficult program flow
 *
 */
public class TemplateDemo {

    public static void main(String[] args) {
        System.out.println("Web order:");
        OrderTemplate webOrder = new WebOrder();
        webOrder.processOrder();

        System.out.println("\nStore Order:");
        OrderTemplate storeOrder = new StoreOrder();
        storeOrder.processOrder();
    }
}
