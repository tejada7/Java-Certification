package com.oca.designPatterns.Structural.composite;

/**
 * Advantages:
 * We know about children classes -> umity among objects
 * Everything si treated the same
 * Leaf and composite have same interface
 * Pitfalls:
 * Overly simplify
 * Costly implementation
 */
public class CompositeDemo {


    public static void main(String[] args) {
        Menu mainMenu = new Menu("Main", "/main");

        MenuItem safetyMenuItem = new MenuItem("Safety", "/safety");

        mainMenu.add(safetyMenuItem);

        Menu claimsSubMenu = new Menu("Claims", "/claims");

        mainMenu.add(claimsSubMenu);

        MenuItem personalClaimsMenu = new MenuItem("Personal Claim", "/personalClaims");

        claimsSubMenu.add(personalClaimsMenu);

        System.out.println(mainMenu.toString());
    }
}
