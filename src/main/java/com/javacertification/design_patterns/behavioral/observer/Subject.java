package com.javacertification.design_patterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Favio on 17/12/2017.
 */
public abstract class Subject {
    private List<Observer> observers = new ArrayList<>();

    abstract void setState(String state);
    abstract String getState();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
