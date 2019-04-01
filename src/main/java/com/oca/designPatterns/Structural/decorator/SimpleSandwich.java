package com.oca.designPatterns.Structural.decorator;

/**
 * Created by Favio on 12/11/2017.
 */
public class SimpleSandwich implements Sandwich {

    @Override
    public String make() {
        return "Bread";
    }
}
