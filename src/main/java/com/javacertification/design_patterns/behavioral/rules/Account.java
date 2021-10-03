package com.javacertification.design_patterns.behavioral.rules;

import java.time.LocalDate;
import java.util.Optional;
import java.util.StringJoiner;

public final class Account {

    private final String accountId;
    private final AccountStatus status;
    private final String confirmationId;
    private final LocalDate lastUpdated;

    public Account(final String accountId) {
        this.accountId = accountId;
        status = AccountStatus.DEFAULT;
        confirmationId = null;
        lastUpdated = null;
    }

    public Account(final String accountId, final AccountStatus status) {
        this.accountId = accountId;
        this.status = status;
        confirmationId = null;
        lastUpdated = null;
    }

    private Account(final String accountId, final AccountStatus status, final String confirmationId) {
        this.accountId = accountId;
        this.status = status;
        this.confirmationId = confirmationId;
        lastUpdated = null;
    }

    private Account(final String accountId, final AccountStatus status, final String confirmationId, final LocalDate lastUpdated) {
        this.accountId = accountId;
        this.status = status;
        this.confirmationId = confirmationId;
        this.lastUpdated = lastUpdated;
    }

    public String getAccountId() {
        return accountId;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public Account updateStatus(final AccountStatus status) {
        return new Account(accountId, status, confirmationId);
    }

    public Account addConfirmationId(final String confirmationId) {
        return new Account(accountId, status, confirmationId);
    }

    public Account removeConfirmationId() {
        return new Account(accountId, status, null);
    }

    public Account updateLastUpdate(final LocalDate lastUpdated) {
        return new Account(accountId, status, confirmationId, lastUpdated);
    }

    @Deprecated
    public Account updateLastUpdate(final Optional<LocalDate> lastUpdated) {
        return new Account(accountId, status, confirmationId, lastUpdated.orElse(null));
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Account.class.getSimpleName() + "[", "]")
                .add("accountId='" + accountId + "'")
                .add("status=" + status)
                .add("confirmationId='" + confirmationId + "'")
                .add("lastUpdated=" + lastUpdated)
                .toString();
    }
}
