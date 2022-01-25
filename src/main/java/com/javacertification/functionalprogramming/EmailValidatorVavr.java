package com.javacertification.functionalprogramming;

import org.apache.commons.lang3.StringUtils;

import java.util.function.Function;
import java.util.regex.Pattern;

import static com.javacertification.functionalprogramming.Result.failure;
import static com.javacertification.functionalprogramming.Result.success;
import static io.vavr.API.Case;
import static io.vavr.API.*;

public class EmailValidatorVavr implements Function<String, Result<String>> {

    private static final Pattern EMAIL_PATTERN = Pattern
            .compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");

    @Override
    public Result<String> apply(final String email) {
        return Match(email).of(
                Case($(StringUtils::isBlank), failure("email must not be empty")),
                Case($(it -> !EMAIL_PATTERN.matcher(it).matches()), failure("invalid email")),
                Case($(), success(email))
        );
    }
}
