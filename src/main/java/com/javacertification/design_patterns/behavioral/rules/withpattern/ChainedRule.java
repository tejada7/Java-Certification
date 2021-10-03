package com.javacertification.design_patterns.behavioral.rules.withpattern;

import com.javacertification.design_patterns.behavioral.rules.AccountStatus;

import java.util.Optional;

public final class ChainedRule implements ChangingAccountStatusRule {

    private final ChangingAccountStatusRule head;
    private final ChangingAccountStatusRule tail;

    ChainedRule(ChangingAccountStatusRule head, ChangingAccountStatusRule tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public Optional<Action> isApplicableTo(AccountStatus status) {
        return head.isApplicableTo(status)
                .map(Optional::of)
                .orElse(tail.isApplicableTo(status));
    }
}
