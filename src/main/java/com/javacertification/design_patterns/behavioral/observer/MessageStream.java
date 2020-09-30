package com.javacertification.design_patterns.behavioral.observer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Favio on 17/12/2017.
 */
public class MessageStream extends Subject {

    private Deque<String> messageHistory = new ArrayDeque<>();

    @Override
    void setState(String message) {
        messageHistory.add(message);
        this.notifyObservers();
    }

    @Override
    String getState() {
        return messageHistory.getLast();
    }
}
