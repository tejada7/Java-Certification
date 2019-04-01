package com.oca.generics.collections;

public class Partner extends Person {

    public Partner(final String name, final int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Partner{} " + super.toString();
    }
}
