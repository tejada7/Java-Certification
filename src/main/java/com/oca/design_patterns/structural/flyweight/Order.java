package com.oca.design_patterns.structural.flyweight;

/**
 * Created by Favio on 12/11/2017.
 */
public class Order {
    private final  int orderNumber;
    private final Item item;

    Order(int orderNumber, Item item) {
        this.orderNumber = orderNumber;
        this.item = item;
    }

    void processOrder() {
        System.out.println("Ordering " + item + " for order number " + orderNumber);
    }
}
