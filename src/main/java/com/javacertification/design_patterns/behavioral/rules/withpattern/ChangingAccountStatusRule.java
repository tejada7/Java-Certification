package com.javacertification.design_patterns.behavioral.rules.withpattern;

import com.javacertification.design_patterns.behavioral.rules.AccountStatus;

import java.util.Optional;

public interface ChangingAccountStatusRule {

    Optional<Action> isApplicableTo(AccountStatus accountStatus);

    default ChangingAccountStatusRule orElse(ChangingAccountStatusRule next) {
        return new ChainedRule(this, next);
    }
}
