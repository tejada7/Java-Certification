package com.oca.designPatterns.behavioral.visitor;

/**
 * Advantages:
 * Separate the algorithm from the object
 * Adding new features
 * Maintain the open/closed principle
 * Externalize change
 * 
 * Pitfalls:
 * Plan for adaptability
 */
public class VisitorDemo {

    public static void main(String[] args) {
        PartsOrder order = new PartsOrder();
        order.addPart(new Wheel());
        order.addPart(new Fender());
        order.addPart(new Oil());

        order.accept(new AtvPartsShippingVisitor());

        order.accept(new AtvPartsDisplayVisitor());
    }
}
