package com.oca.designPatterns.Structural.flyweight;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Favio on 12/11/2017.
 */
public class InventorySystem {

    private final Catalog catalog = new Catalog();
    private final List<Order> orders = new CopyOnWriteArrayList<>();

    void takeOrder(String itemName, int orderNumber) {
        Item item = catalog.lookup(itemName);
        Order order = new Order(orderNumber, item);
        orders.add(order);
    }

    void process() {
        for (Order order : orders) {
            order.processOrder();
            orders.remove(order);
        }
    }

    String report() {
        return "\n Total Item objects made: " + catalog.totalItemsMade();
    }
}
