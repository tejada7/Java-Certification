package com.javacertification.multithreading.producerconsumerpattern;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

/**
 * This class demonstrates the usage of wait/notify in a single-shared lock, namely producer/consumer pattern.
 */
public class ProducerConsumerCondition {

    private static Logger logger = Logger.getLogger(ProducerConsumerCondition.class.getName());
    private static final Lock lock = new ReentrantLock();
    private static final Condition notFull = lock.newCondition();
    private static final Condition notEmpty = lock.newCondition();

    private static int[] buffer;
    private static int count;

    static boolean isEmpty() {
        return 0 == count;
    }

    static boolean isFull() {
        return count == buffer.length;
    }

    static class Producer {
        void produce() {
            try {
                lock.lock();
                while (isFull()) {
                    notFull.await();
                }
                buffer[count++] = 1;
                notEmpty.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }

    static class Consumer {
        void consume() {
            try {
                lock.lock();
                while (isEmpty()) {
                    notEmpty.await();
                }
                buffer[--count] = 0;
                notFull.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

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
