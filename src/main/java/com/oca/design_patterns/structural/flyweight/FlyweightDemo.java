package com.oca.design_patterns.structural.flyweight;

/**
 * Advantages:
 * Focused on memory optimization
 * Deals with immutable objects
 * Pitfalls:
 * Complex pattern, lots of pieces involved
 * Patterns inside a pattern (factory embedded)
 */
public class FlyweightDemo {
    public static void main(String[] args) {
        InventorySystem ims = new InventorySystem();

        ims.takeOrder("Roomba", 114);
        ims.takeOrder("Bose écouteurs", 361);
        ims.takeOrder("Samsung TV", 654);
        ims.takeOrder("Bose écouteurs", 361);
        ims.takeOrder("Roomba", 114);
        ims.takeOrder("Iphone 6S", 584);
        ims.takeOrder("Samsung TV", 654);
        ims.takeOrder("Bose écouteurs", 361);
        ims.takeOrder("Roomba", 114);
        ims.takeOrder("Iphone 6S", 585);
        ims.takeOrder("Bose écouteurs", 361);;
        ims.takeOrder("Samsung TV", 654);

        ims.process();

        System.out.println(ims.report());
    }
}
