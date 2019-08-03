package com.oca.miscellaneous;

import java.util.concurrent.*;

public class PlayingWithExecutorsAndRunnables {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        final String toPrint = "Running from thread: " + Thread.currentThread().getName();
        Runnable runnable = () -> System.out.println(toPrint);

        firstApproach(runnable);
        secondApproach(runnable);
        thirdApproach(toPrint);
    }

    private static void thirdApproach(String toPrint) throws ExecutionException, InterruptedException, TimeoutException {
        Callable<String> callable = () -> {
            Thread.sleep(50);
            return toPrint;
        };
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            final Future<String> future = executorService.submit(callable);
            System.out.println("I get: " + future.get(100, TimeUnit.MILLISECONDS));
        }
        executorService.shutdown();
    }

    private static void secondApproach(Runnable runnable) {
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            executorService.execute(runnable);
        }
        executorService.shutdown();
    }

    private static void firstApproach(Runnable runnable) {
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }
}
