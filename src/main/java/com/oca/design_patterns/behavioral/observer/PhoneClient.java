package com.oca.design_patterns.behavioral.observer;

/**
 * Created by Favio on 17/12/2017.
 */
public class PhoneClient extends Observer {

    public PhoneClient(Subject subject) {
        this.subject = subject;
        subject.attach(this);
    }

    public void addMessage(String message) {
        subject.setState(message + " - sent from phone.");
    }

    @Override
    void update() {
        System.out.println("Phone Stream: " + subject.getState());
    }
}
