package multiThreading.scheduler;

/**
 * Created by Favio on 7/1/2018.
 */
public class SchedulerDemo {

    private static Object lock = new Object();
    static int count;
    static int[] buffer;


    static class Consumer {
        void consume() {
            synchronized (lock) {
                if (isEmpty(buffer)) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                buffer[count--] = 0;
                lock.notify();
            }
        }
    }

    static class Producer {
        void produce() {
            synchronized (lock) {
                if (isFull(buffer)) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                buffer[count++] = 1;
                lock.notify();
            }
        }
    }

    static boolean isEmpty(int buffer[]) {
        return count == 0;
    }

    static boolean isFull(int buffer[]) {
        return count == buffer.length;
    }

    public static void main(String[] args) throws InterruptedException {
        buffer = new int[50];

        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        Runnable produceTask = () -> {
            for (int i = 0; i < 50; i++) {
                producer.produce();
            }
            System.out.println("Done producing.");
        };

        Runnable consumeTask = () -> {
            for (int i = 0; i < 50; i++) {
                consumer.consume();
            }
            System.out.println("Done consuming.");
        };

        Thread consumerThread = new Thread(consumeTask);
        Thread producerThread = new Thread(produceTask);

        consumerThread.start();
        producerThread.start();

        consumerThread.join();
        producerThread.join();

        System.out.println("Data in the buffer " + count);
    }
}
