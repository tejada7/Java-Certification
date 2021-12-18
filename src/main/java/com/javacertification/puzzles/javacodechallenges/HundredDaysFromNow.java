package com.javacertification.puzzles.javacodechallenges;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.UnaryOperator;

public class HundredDaysFromNow implements UnaryOperator<LocalDate> {

    @Override
    public LocalDate apply(final LocalDate localDate) {
        return localDate.plus(Period.ofDays(100));
    }
}
