package com.javacertification.design_patterns.behavioral.rules;

import java.util.UUID;

public interface AccountProviderSpi {
    String getConfirmationId(Account account);

    static AccountProviderSpi defaultProvider() {
        return account -> UUID.randomUUID().toString();
    }
}
