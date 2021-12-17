package com.javacertification.puzzles.javacodechallenges;

import java.util.function.Predicate;

import static java.lang.Character.*;
import static java.util.Objects.requireNonNull;
import static java.util.Optional.of;

/**
 * At least 6 characters, 1 uppercase letter, 1 lowercase letter and 1 number.
 */
public class IsPasswordComplexEnough implements Predicate<String> {

    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{6,20}$";

    @Override
    public boolean test(final String password) {
//        return password.matches(PASSWORD_PATTERN);
        return of(requireNonNull(password, "password must be present.")).stream()
                .allMatch(atLeastSixCharacters()
                        .and(atLeastOneUpperCase())
                        .and(atLeastOneLowerCase())
                        .and(atLeastOneNumber()));
    }

    private static Predicate<String> atLeastSixCharacters() {
        return string -> string.strip().replaceAll(" ", "").length() >= 6;
    }

    private static Predicate<String> atLeastOneUpperCase() {
        return string -> string.chars().anyMatch(value -> isUpperCase((char) value));
    }

    private static Predicate<String> atLeastOneLowerCase() {
        return string -> string.chars().anyMatch(value -> isLowerCase((char) value));
    }

    private static Predicate<String> atLeastOneNumber() {
        return string -> string.chars().anyMatch(value -> isDigit((char) value));
    }
}
