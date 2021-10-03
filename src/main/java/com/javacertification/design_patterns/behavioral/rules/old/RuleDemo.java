package com.javacertification.design_patterns.behavioral.rules.old;

import com.javacertification.design_patterns.behavioral.rules.Account;
import com.javacertification.design_patterns.behavioral.rules.AccountProviderSpi;
import com.javacertification.design_patterns.behavioral.rules.AccountStatus;
import com.javacertification.design_patterns.behavioral.rules.EmailSpi;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public final class RuleDemo {

    private final EmailSpi emailSpi;
    private final AccountProviderSpi accountConfirmationIdProvider;

    public RuleDemo() {
        emailSpi = EmailSpi.defaultSender();
        accountConfirmationIdProvider = AccountProviderSpi.defaultProvider();
    }

    public void run() {
        final var params = Map.of(new Account("account1"), AccountStatus.DEFAULT,
                new Account("account2"), AccountStatus.ACTIVE,
                new Account("account3"), AccountStatus.PENDING,
                new Account("account4"), AccountStatus.PENDING_CONFIRMATION,
                new Account("account5"), AccountStatus.DISABLED
        );

        Function<AccountStatus, Optional<LocalDate>> localDateSupplier = (status) -> Optional.ofNullable(status)
                .filter(Predicate.not(AccountStatus.DEFAULT::equals)).stream()
                .findAny()
                .map(s -> LocalDate.now());

        params.forEach((account, status) -> System.out.println("The updated account is:" + handleAccountUpdate(account, status, localDateSupplier.apply(status)) + "\n"));
    }

    private Account handleAccountUpdate(Account account, AccountStatus status, Optional<LocalDate> lastUpdate) {
        Account updatedAccount = null;
        switch (status) {
            case DEFAULT:
                updatedAccount = account;
                break;
            case ACTIVE:
                final var confirmationId = accountConfirmationIdProvider.getConfirmationId(account);
                updatedAccount = account.addConfirmationId(confirmationId);
                updatedAccount = updatedAccount.updateStatus(AccountStatus.ACTIVE);
                updatedAccount = updatedAccount.updateLastUpdate(lastUpdate);
                emailSpi.sendEmail("user@user.com", "Your account is now active.");
                break;
            case PENDING:
                emailSpi.sendEmail("support@support.com", "Account pending, status will be updated to PENDING_CONFIRMATION.");
                emailSpi.sendEmail("user@user.com", "Account waiting for confirmation.");
                updatedAccount = account.updateStatus(AccountStatus.PENDING_CONFIRMATION);
                updatedAccount = updatedAccount.updateLastUpdate(lastUpdate);
                break;
            case PENDING_CONFIRMATION:
                emailSpi.sendEmail("support@support.com", "Account waiting for confirmation.");
                emailSpi.sendEmail("user@user.com", "Account waiting for confirmation.");
                updatedAccount = account;
                updatedAccount = updatedAccount.updateLastUpdate(lastUpdate);
                break;
            case DISABLED:
                emailSpi.sendEmail("user@user.com", "Your account " + account.getAccountId() + " has been disabled.");
                emailSpi.sendEmail("support@support.com", "Account disabled.");
                updatedAccount = account.removeConfirmationId();
                updatedAccount = updatedAccount.updateLastUpdate(lastUpdate);
                break;
            default:
                // Do Nothing
        }
        return updatedAccount;
    }
}
