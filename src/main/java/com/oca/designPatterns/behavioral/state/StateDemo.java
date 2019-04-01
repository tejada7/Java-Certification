package com.oca.designPatterns.behavioral.state;

/**
 * Advantages:
 * State of the application is stored in an object rather than in a bunch of variables throughout the entire application
 * Open-close principle in software design: the classes are close to changes but the states are open to extensions
 * Reduces cyclomatic complexity
 *
 * Pitfalls:
 * Know all states of the application
 * Keep logic out of context
 * More classes involved
 */
public class StateDemo {

    public static void main(String[] args) {
        Fan fan = new Fan();
        // Off
        System.out.println(fan);

        // Low
        fan.pullChain();
        System.out.println(fan);

        // Medium
        fan.pullChain();
        System.out.println(fan);

        // High
        fan.pullChain();
        System.out.println(fan);

        // Off
        fan.pullChain();
        System.out.println(fan);
    }
}
