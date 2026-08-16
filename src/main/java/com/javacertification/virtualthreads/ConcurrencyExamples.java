package com.javacertification.virtualthreads;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

import static com.javacertification.virtualthreads.ConcurrencyExamples.DummyMethods.*;

class ConcurrencyExamples {

    static class DummyMethods {

        private static final String API_DATA = "apiData";
        private static final String DB_ID = "dbId";

        public static String hitApi() {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return API_DATA;
        }

        public static String storeInDb() {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return DB_ID;
        }

        public static void executeSomethingImportant(String a, String b) {
            try {
                Thread.sleep(200);
                if (!a.equals(API_DATA) || !b.equals(DB_ID)) {
                    throw new IllegalStateException("something went wrong");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    void naiveImperativeSolution() {
        executeSomethingImportant(hitApi(), storeInDb());
    }

    void simpleThreadSolution(final Thread.Builder threadBuilderSupplier) {
        final var aTaskResult = new AtomicReference<String>();
        final var bTaskResult = new AtomicReference<String>();
        final Thread threadA = threadBuilderSupplier.unstarted(() -> {
            aTaskResult.set(hitApi());
        });
        final Thread threadB = threadBuilderSupplier.unstarted(() -> {
            bTaskResult.set(storeInDb());
        });
        Stream.of(threadA, threadB).forEach(Thread::start);
        Stream.of(threadA, threadB).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        executeSomethingImportant(aTaskResult.get(), bTaskResult.get());
    }

    void executorServicesSolution(ExecutorService executorService) {
        try (final var executor = executorService) {
            final Future<String> taskA = executor.submit(DummyMethods::hitApi);
            final Future<String> taskB = executor.submit(DummyMethods::storeInDb);
            executor.execute(() -> {
                try {
                    executeSomethingImportant(taskA.get(), taskB.get());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    void completableFutureSolution() {
        try {
            CompletableFuture.supplyAsync(DummyMethods::hitApi)
                .thenAcceptBothAsync(CompletableFuture.supplyAsync(DummyMethods::storeInDb),
                    DummyMethods::executeSomethingImportant).get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    void forkJoinPoolSolution() {
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            forkJoinPool.invoke(new RecursiveTask<Void>() {
                @Override
                protected Void compute() {
                    final var forkJoinTaskA = ForkJoinTask.adapt(DummyMethods::hitApi);
                    final var forkJoinTaskB = ForkJoinTask.adapt(DummyMethods::storeInDb);

                    ForkJoinTask.invokeAll(forkJoinTaskA, forkJoinTaskB);

                    executeSomethingImportant(forkJoinTaskA.getRawResult(), forkJoinTaskB.getRawResult());
                    return null;
                }
            });
        }
    }

    void structuredConcurrencySolution() {
        try (var scope = StructuredTaskScope.open()) {
            final var apiHitSubtask = scope.fork(DummyMethods::hitApi);
            final var dbStorageSubtask = scope.fork(DummyMethods::storeInDb);
            scope.join();
            DummyMethods.executeSomethingImportant(apiHitSubtask.get(), dbStorageSubtask.get());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            throw new RuntimeException("Subtask failed", e);
        }
    }

    void main() {
        measure("Naive imperative solution", this::naiveImperativeSolution);
        measure("Platform threads", () -> simpleThreadSolution(Thread.ofPlatform()));
        measure("Virtual threads", () -> simpleThreadSolution(Thread.ofVirtual()));
        measure("Executor service with virtual threads",
            () -> executorServicesSolution(Executors.newVirtualThreadPerTaskExecutor()));
        measure("Executor service with thread pool",
            () -> executorServicesSolution(Executors.newFixedThreadPool(3)));
        measure("Completable future solution", this::completableFutureSolution);
        measure("Fork join pool", this::forkJoinPoolSolution);
        measure("Structured concurrency solution", this::structuredConcurrencySolution);
    }

    private static void measure(String title, Runnable task) {
        final long startTime = Instant.now().toEpochMilli();
        try {
            task.run();
        } finally {
            final long endTime = Instant.now().toEpochMilli();
            IO.println(title + " - Execution time: " + Duration.ofMillis(endTime - startTime).toMillis());
        }
    }

    private static <T> T measure(Callable<T> task) throws Exception {
        final long startTime = Instant.now().toEpochMilli();
        try {
            return task.call();
        } finally {
            final long endTime = Instant.now().toEpochMilli();
            IO.println("Execution time: " + Duration.ofMillis(endTime - startTime).toMillis());
        }
    }
}
