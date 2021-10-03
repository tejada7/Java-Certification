package com.javacertification.design_patterns.behavioral.rules.withpattern;

import com.javacertification.design_patterns.behavioral.rules.AccountStatus;

import java.util.Optional;

public final class StatusEqualityRule implements ChangingAccountStatusRule, Action {

    private final AccountStatus pattern;
    private final Runnable action;

    private StatusEqualityRule(final AccountStatus status, final Runnable action) {
        this.pattern = status;
        this.action = action;
    }

    public static ChangingAccountStatusRule match(AccountStatus pattern, Runnable action) {
        return new StatusEqualityRule(pattern, action);
    }

    @Override
    public Optional<Action> isApplicableTo(AccountStatus status) {
        return pattern == status ? Optional.of(this) : Optional.empty();
    }

    @Override
    public void apply() {
        action.run();
    }
}
