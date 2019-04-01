package com.oca.designPatterns.behavioral.template;

/**
 * Created by Favio on 25/12/2017.
 */
public class StoreOrder extends OrderTemplate {
    @Override
    public void doCheckot() {
        System.out.println("Ring up items from cart.");
    }

    @Override
    public void doPayment() {
        System.out.println("Process payment with Card present");
    }

    @Override
    public void doReceipt() {
        System.out.println("Bag items at counter");
    }

    @Override
    public void doDelivery() {
        System.out.println("Print receipt");
    }
}
