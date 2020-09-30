package com.javacertification.miscellaneous;

/**
 * Created by Favio on 30/8/2017.
 */
public class SynchronizedCounter {

    private Integer count = 0;

    private synchronized void increment() {
        count++;
    }

    public static void main(String[] args) {
        SynchronizedCounter synchronizedCounter = new SynchronizedCounter();
        synchronizedCounter.work();
    }

    private void work() {

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                System.out.println("T1: " + count);
                increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                System.out.println("T2: " + count);
                increment();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The Count is : " + count);
    }
}