package com.javacertification.ocp.concurrency;

import java.util.List;
import java.util.concurrent.*;

public class Concurrency {

    public static void main(String[] args) {
        List<Runnable> runnables = List.of(
                () -> System.out.println("From runnable: " + Thread.currentThread()),
                () -> System.out.println("From runnable 1: " + Thread.currentThread()),
                () -> System.out.println("From runnable 2: " + Thread.currentThread()),
                () -> System.out.println("From runnable 3: " + Thread.currentThread()));

        List<Callable<String>> callables = List.of(
                () -> "From callable",
                () -> "From callable1",
                () -> "From callable2",
                () -> "From callable3");

//        singleThreadExecutor(runnables, callables);


        var scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//        scheduledExecutorService.schedule(runnables.get(0), 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleWithFixedDelay(runnables.get(0), 1, 2, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(runnables.get(0), 1, 2, TimeUnit.SECONDS);
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    private static void singleThreadExecutor(List<Runnable> runnables, List<Callable<String>> callables) {
        var executorService = Executors.newSingleThreadExecutor();
        try {
            List<Future<String>> submitCallables = executorService.invokeAll(callables);
            runnables.forEach(executorService::submit);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (executorService != null) {
                System.out.println("Terminating... " + Thread.currentThread());
                executorService.shutdown();
            }
        }
    }
}
