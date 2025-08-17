package com.javacertification;

import java.util.function.Function;

public class WildcardFunction {

    public <T> void addClassFilter(Function<String, T> f) {
        System.out.println("The type of f is: " + f.getClass().getGenericSuperclass());
//        f.getClass().getGenericSuperclass()
    }

    public static void main(String[] args) {

        Function<String, Integer> f1 = s -> 1;
        new WildcardFunction().addClassFilter(f1);
    }
}
