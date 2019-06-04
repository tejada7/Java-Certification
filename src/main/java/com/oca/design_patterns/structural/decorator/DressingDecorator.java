package com.oca.design_patterns.structural.decorator;

/**
 * Created by Favio on 12/11/2017.
 */
public class DressingDecorator extends SandwichDecorator {


    public DressingDecorator(Sandwich customSandwich) {
        super(customSandwich);
    }

    public String make() {
        return customSandwich.make() + addDressing();
    }

    private String addDressing() {
        return " + mustard";
    }
}
