package com.javacertification.design_patterns.structural.facade;

/**
 * Advantages:
 * API easier to use
 * Hide details to the client
 * Reduce dependencies on outside code
 * Simplifies interfaces or client uses
 * ~refactoring pattern
 *
 * Pitfalls:
 * Use to clean up code
 * Deals with flat structures
 *
 */
public class FacadeDemo {
    public static void main(String[] args) {
        JdbcFacade jdbcFacade = new JdbcFacade();

        System.out.println(jdbcFacade.createTable());

        System.out.println(jdbcFacade.insertIntoTable());

        System.out.println(jdbcFacade.getAddresses());
    }
}
