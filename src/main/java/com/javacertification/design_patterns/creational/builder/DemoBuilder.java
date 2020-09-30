package com.javacertification.design_patterns.creational.builder;

import java.time.LocalDate;

/**
 * Advantages:
 * Flexibility in creation of object
 *
 * Pitfalls:
 * It may seems code replication for each model class
 * Immutable
 * Complexity
 * Inner static class
 */
public class DemoBuilder {
    public static void main(String[] args) {
        IdentityBean.IdentityBeanBuilder builder = new IdentityBean.IdentityBeanBuilder();
        IdentityBean instance = builder.withId(1).withBirthDate(LocalDate.now()).build();
        System.out.println(instance);
    }
}
