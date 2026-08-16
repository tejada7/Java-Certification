package com.javacertification.virtualthreads;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.runAsync;
import static org.assertj.core.api.BDDAssertions.then;

@DisplayNameGeneration(ReplaceUnderscores.class)
@ExtendWith(ConcurrencyExamplesTest.TimingExtension.class)
class ConcurrencyExamplesTest {

    private static final ConcurrencyExamples concurrencyExamples = new ConcurrencyExamples();
    private static final Duration IMPROVED_TIMEOUT = Duration.ofMillis(450);
    private static final Duration ORIGINAL_TIMEOUT = Duration.ofMillis(650);

    private static Stream<Arguments> should_execute_under_timeout() {
        return Stream.of(
            Arguments.of("Platform threads",
                ((Runnable) () -> concurrencyExamples.simpleThreadSolution(Thread.ofPlatform())),
                IMPROVED_TIMEOUT),
            Arguments.of("Virtual threads",
                ((Runnable) () -> concurrencyExamples.simpleThreadSolution(Thread.ofVirtual())),
                IMPROVED_TIMEOUT),
            Arguments.of("Executor service with thread pool",
                ((Runnable) () -> concurrencyExamples.executorServicesSolution(Executors.newFixedThreadPool(2))),
                IMPROVED_TIMEOUT),
            Arguments.of("Executor service with virtual threads per task",
                ((Runnable) () -> concurrencyExamples.executorServicesSolution(Executors.newVirtualThreadPerTaskExecutor())),
                IMPROVED_TIMEOUT),
            Arguments.of("Completable future",
                ((Runnable) concurrencyExamples::completableFutureSolution),
                IMPROVED_TIMEOUT),
            Arguments.of("Fork join pool",
                ((Runnable) concurrencyExamples::forkJoinPoolSolution),
                IMPROVED_TIMEOUT),
            Arguments.of("Structured concurrency",
                ((Runnable) concurrencyExamples::structuredConcurrencySolution),
                IMPROVED_TIMEOUT),
            Arguments.of("Naive implementation",
                ((Runnable) concurrencyExamples::naiveImperativeSolution),
                ORIGINAL_TIMEOUT)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource
    void should_execute_under_timeout(String description, Runnable solution, Duration expectedTimeout) {
        then(runAsync(solution))
            .succeedsWithin(expectedTimeout);
    }

    static class TimingExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

        private static final String START_TIME = "start_time";

        @Override
        public void beforeTestExecution(ExtensionContext context) {
            getStore(context).put(START_TIME, System.currentTimeMillis());
        }

        @Override
        public void afterTestExecution(ExtensionContext context) {
            long startTime = getStore(context).remove(START_TIME, long.class);
            long duration = System.currentTimeMillis() - startTime;

            System.out.printf("--> [%s] took %d ms%n", context.getDisplayName(), duration);
        }

        private ExtensionContext.Store getStore(ExtensionContext context) {
            return context.getStore(ExtensionContext.Namespace.create(getClass(), context.getRequiredTestMethod()));
        }
    }
}
