package com.javacertification.design_patterns.behavioral.observer;

/**
 * Created by Favio on 17/12/2017.
 */
public abstract class Observer {
    protected Subject subject;
    abstract void update();
}
