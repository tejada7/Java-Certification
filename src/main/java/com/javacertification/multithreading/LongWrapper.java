package com.javacertification.multithreading;

/**
 * Created by Favio on 7/1/2018.
 */
public class LongWrapper {
    private Object key = new Object();

    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public void incrementValue() {
        synchronized (key) {
            value = value + 1;
        }
    }
}
