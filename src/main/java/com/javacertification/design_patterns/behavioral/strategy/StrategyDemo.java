package com.javacertification.design_patterns.behavioral.strategy;

/**
 * Advantages:
 * Algorithm to be selected at runtime
 * Eliminate conditional statements
 *
 * Pitfalls:
 * Increased number of classes
 *
 */
public class StrategyDemo {

    public static void main(String[] args) {
        CreditCard amexCard = new CreditCard(new AmexStrategy());
        amexCard.setNumber("379185883464283");
        amexCard.setDate("04/2020");
        amexCard.setCvv("123");
        System.out.println("Is Amex valid: " + amexCard.isValid());

        CreditCard amexCard1 = new CreditCard(new AmexStrategy());
        amexCard1.setNumber("37918588346428");
        amexCard1.setDate("04/2020");
        amexCard1.setCvv("123");
        System.out.println("Is Amex1 valid: " + amexCard1.isValid());

        CreditCard visaCard = new CreditCard(new VisaStrategy());
        visaCard.setNumber("4539589763663402");
        visaCard.setDate("04/2020");
        visaCard.setCvv("123");
        System.out.println("Is Visa valid: " + visaCard.isValid());
    }
}
