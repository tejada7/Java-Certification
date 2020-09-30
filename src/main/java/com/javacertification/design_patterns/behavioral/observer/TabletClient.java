package com.javacertification.design_patterns.behavioral.observer;

/**
 * Created by Favio on 17/12/2017.
 */
public class TabletClient extends Observer {

    public TabletClient(Subject subject) {
        this.subject = subject;
        subject.attach(this);
    }

    public void addMessage(String message) {
        subject.setState(message + " - sent from table");
    }

    @Override
    void update() {
        System.out.println("Tablet Stream: " + subject.getState());
    }
}
