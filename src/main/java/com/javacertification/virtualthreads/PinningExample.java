package com.javacertification.virtualthreads;

import java.util.List;
import java.util.stream.IntStream;

class PinningExample {

    void main() {
        final Object lock = new Object();
        final List<Thread> threads = IntStream.range(0, 10)
            .mapToObj(i -> Thread.ofVirtual().unstarted(() -> {
                    if (i == 0) {
                        IO.println(Thread.currentThread());
                    }
                    synchronized (lock) {
                        try {
                            Thread.sleep(25);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        if (i == 0) {
                            IO.println(Thread.currentThread());
                            // Before Java 24, due to pinning both threads of line 13 and 23 are the same,
                            // which is inefficient
                        }
                    }
                }

            )).toList();
            threads.forEach(Thread::start);
            threads.forEach(it -> {
                try {
                    it.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
    }
}
