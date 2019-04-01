package com.oca.designPatterns.Structural.flyweight;

/**
 * Instances of the Item will be the flyweights (it'll be instantiated various times)
 * */
public class Item {
    private final String name;

    public Item(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
