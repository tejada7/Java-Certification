package com.javacertification.miscellaneous;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SimpleClass {

    public static void main(String[] args) {
        final var dayOfWeek = LocalDate.now().with(DayOfWeek.FRIDAY).getDayOfWeek();
        switch (dayOfWeek) {
            case SATURDAY:
                 FRIDAY:
                System.out.println("joder");
            default:
                System.out.println("default");
        }
    }
}
