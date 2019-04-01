package com.oca.multiThreading.producerConsumerPattern;

import java.util.logging.Logger;

/**
 * This class demonstrates the usage of wait/notify in a single-shared lock, namely producer/consumer pattern.
 */
public class ProducerConsumer {

    private static Logger logger = Logger.getLogger(ProducerConsumer.class.getName());

    private static final Object lock = new Object();

    private static int[] buffer;
    private static int count;

    /**
     * As long as the buffer is full, the lock is freed so that another thread can own it, otherwise, an
     * element of index {@link #count} is set to 1 and the lock is released.
     */
    static class Producer {
        void produce() {
            synchronized (lock) {
                if (isFull()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException exception) {
                        logger.log(java.util.logging.Level.SEVERE, "Checked exception thrown", exception);
                    }
                }
                buffer[count++] = 1;
                lock.notifyAll();
            }
        }
    }

    /**
     * If the buffer is empty, the lock is freed, letting another thread to own it, or else, an element of index
     * {@link #count} is set to 0 and the lock released.
     */
    static class Consumer {
        void consume() {
            synchronized (lock) {
                if (isEmpty()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException exception) {
                        logger.log(java.util.logging.Level.SEVERE, "Checked exception thrown", exception);
                    }
                }
                buffer[--count] = 1;
                lock.notifyAll();
            }
        }
    }

    static boolean isEmpty() {
        return 0 == count;
    }

    static boolean isFull() {
        return count == buffer.length;
    }

    public static void main(String[] args) throws InterruptedException {
        buffer = new int[50];
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        Thread producerTask = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                producer.produce();
            }
        });
        Thread consumerTask = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                consumer.consume();
            }
        });

        producerTask.start();
        consumerTask.start();

        // Wait for the threads to finish
        producerTask.join();
        consumerTask.join();

        System.out.printf("The size of the buffer is %d", count);
    }
}
