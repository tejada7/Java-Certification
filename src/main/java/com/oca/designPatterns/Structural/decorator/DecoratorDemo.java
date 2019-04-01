package com.oca.designPatterns.Structural.decorator;

/**
 * Advantages:
 * Contains another entity -> composition
 * Modifies behavior
 * Does not change underlying object
 *
 * Pitfalls:
 * Multiple specialized objects
 * Confused with simple inheritance
 */
public class DecoratorDemo {

    public static void main(String[] args) {
        Sandwich sandwich = new DressingDecorator(new MeatDecorator(new SimpleSandwich()));
        System.out.println(sandwich.make());
    }
}
