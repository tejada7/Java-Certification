package com.javacertification.design_patterns.behavioral.observer;

/**
 * Advatages:
 * Decoupling one-to-many objects
 * Event handling
 * One-to-many one subject to many observers
 *
 * Pitfalls:
 * Unexpected updates
 * Performance hit
 * Debugging difficult
 */
public class ObserverDemo {

    public static void main(String[] args) {
        Subject subject = new MessageStream();

        PhoneClient phoneClient = new PhoneClient(subject);
        TabletClient tabletClient = new TabletClient(subject);

        phoneClient.addMessage("Here is a new message!");
        tabletClient.addMessage("Another new message!");
    }
}
