package com.javacertification.functionalprogramming;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;

import static com.javacertification.functionalprogramming.Case.match;
import static com.javacertification.functionalprogramming.Case.matchCase;
import static com.javacertification.functionalprogramming.Result.failure;
import static com.javacertification.functionalprogramming.Result.success;
import static java.lang.System.err;
import static java.lang.System.out;

public class EmailValidatorFunctional implements Function<String, Result<String>> {

    private static final Pattern EMAIL_PATTERN = Pattern
            .compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");

    @Override
    public Result<String> apply(final String email) {
        return match(
                matchCase(() -> success(email)),
                matchCase(() -> email == null, () -> failure("email must not be null")),
                matchCase(() -> email.strip().length() == 0, () -> failure("email must not be empty")),
                matchCase(() -> !EMAIL_PATTERN.matcher(email).matches(), () -> failure("invalid email"))
        );
    }

    public static void main(String[] args) {
        final var emailValidator = new EmailValidatorFunctional();

        emailValidator.apply("john.doe@acme.com").bind(sendVerificationMail(), logError());
        emailValidator.apply("  ").bind(sendVerificationMail(), logError());
        emailValidator.apply(null).bind(sendVerificationMail(), logError());
    }

    private static Consumer<String> sendVerificationMail() {
        return email -> out.println("Verification mail sent to " + email);
    }

    private static Consumer<String> logError() {
        return message -> err.println("Error message logged " + message);
    }
}
