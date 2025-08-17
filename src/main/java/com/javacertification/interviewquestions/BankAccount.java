package com.javacertification.interviewquestions;

import java.time.InstantSource;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public interface BankAccount {
    void deposit(int amount);

    void withdraw(int amount);

    void printStatement();
}

interface ACLBankAccount<UNITY> {
    UnaryOperator<UNITY> deposit(UNITY amount);

    UnaryOperator<UNITY> withdraw(UNITY amount);

    void printStatement();
}

record Transaction(LocalDate date, int amount, int balance) {

    @Override
    public String toString() {
        return date + "\t||\t" + amount + "\t||\t" + balance;
    }
}

final class SimpleBankAccount implements BankAccount {

    private final List<Transaction> transaction;
    private final InstantSource instantSource;

    SimpleBankAccount(InstantSource instantSource) {
        this.instantSource = instantSource;
        transaction = new ArrayList<>();
    }

    @Override
    public void deposit(int amount) {
        transaction.add(new Transaction(currentDate(), amount, calculateBalance(amount)));
    }

    @Override
    public void withdraw(int amount) {
        transaction.add(new Transaction(currentDate(), -amount, calculateBalance(-amount)));
    }

    /**
     * Date       || Amount || Balance
     * 2012-01-14 || -500   || 2500
     */
    @Override
    public void printStatement() {
        System.out.println("Date\t||\tAmount\t||\tBalance");
        transaction.reversed()
            .forEach(System.out::println);
    }

    private LocalDate currentDate() {
        return LocalDate.now(instantSource.withZone(ZoneOffset.UTC));
    }

    private int calculateBalance(final int amount) {
        return transaction.stream()
            .map(Transaction::balance)
            .reduce(0, Integer::sum) + amount;
    }
}
