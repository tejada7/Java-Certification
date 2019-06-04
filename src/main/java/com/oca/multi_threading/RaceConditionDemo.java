package com.oca.multi_threading;

/**
 * Created by Favio on 7/1/2018.
 */
public class RaceConditionDemo {

    public static void main(String[] args) throws InterruptedException {
        LongWrapper lw = new LongWrapper();

        // Incrementing its value x1000 times
        Runnable runnable = () -> {
//            System.out.println("Running from: " + Thread.currentThread().getName());
            for (int i = 0; i < 1_000; i++) {
                lw.incrementValue();
            }
        };

//        exampleOneThread(lw, runnable);

        exampleManyThreads(lw, runnable);
    }

    private static void exampleOneThread(LongWrapper lw, Runnable runnable) throws InterruptedException {
        Thread t1 = new Thread(runnable);

        t1.setName("My Thread");

        // Launching the thread
        t1.start();

        t1.join();

        System.out.println("The value is: " + lw.getValue());
        // t1.run(); // NEVER, this runs the thread from the main one, regardless the assigned one in the constructor
    }

    private static void exampleManyThreads(LongWrapper lw, Runnable runnable) throws InterruptedException {
        Thread threads[] = new Thread[1_000];

        // Initializing
        for (int i = 0; i < 1_000; i++) {
            threads[i] = new Thread(runnable);
            threads[i].start();
        }

        // Making sure that runnable has been correctly executed, not sure
        for (Thread t : threads) {
            t.join();
            t.stop();
        }
        System.out.println("The value is: " + lw.getValue());
    }
}
