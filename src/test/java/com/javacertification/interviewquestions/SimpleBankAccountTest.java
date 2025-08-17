package com.javacertification.interviewquestions;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.time.*;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

import static org.assertj.core.api.BDDAssertions.then;

@DisplayNameGeneration(ReplaceUnderscores.class)
class SimpleBankAccountTest {

    private final LocalDate date = LocalDate.of(2024, 1, 1);
    private final InstantSource instantSource = InstantSource
            .fixed(LocalDateTime.of(date, LocalTime.of(0, 0))
                    .toInstant(ZoneOffset.UTC));
    private final BankAccount bankAccount = new SimpleBankAccount(instantSource);

    @Test
    void should_deposit() {
        // Given
        final var logConsole = initLogConsole();
        final var anAmount = 1000;
        final var expectedBalance = 1000;

        // When
        bankAccount.deposit(anAmount);
        bankAccount.printStatement();

        // Then
        then(logConsole.toString())
                .contains("""
                        Date\t||\tAmount\t||\tBalance
                        %s\t||\t%s\t||\t%s
                        """.formatted(date, anAmount, expectedBalance));
    }

    @Test
    void should_withdraw() {
        // Given
        final var logConsole = initLogConsole();
        final var anAmount = 1000;
        final var expectedBalance = 1000;

        // When
        bankAccount.deposit(anAmount);
        bankAccount.withdraw(anAmount / 2);
        bankAccount.printStatement();

        // Then
        then(logConsole.toString())
                .contains("""
                        Date\t||\tAmount\t||\tBalance
                        %s\t||\t%s\t||\t%s
                        %s\t||\t%s\t||\t%s
                        """.formatted(
                        date, -anAmount / 2, expectedBalance / 2,
                        date, anAmount, expectedBalance
                ));
    }

    private static ByteArrayOutputStream initLogConsole() {
        final ByteArrayOutputStream logConsole = new ByteArrayOutputStream();
        System.setOut(new PrintStream(logConsole));
        return logConsole;
    }
}
