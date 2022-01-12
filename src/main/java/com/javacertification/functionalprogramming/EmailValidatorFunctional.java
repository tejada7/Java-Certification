package com.javacertification.functionalprogramming;

import com.javacertification.functionalprogramming.EmailValidatorFunctional.Result.Failure;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;

import static com.javacertification.functionalprogramming.EmailValidatorFunctional.*;
import static com.javacertification.functionalprogramming.EmailValidatorFunctional.Result.Success;
import static java.lang.System.*;
import static java.lang.System.out;

public class EmailValidatorFunctional implements Function<String, Result<String>> {

    private static final Pattern EMAIL_PATTERN
            = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");

    @Override
    public Result<String> apply(final String email) {
        return (email == null)
                ? new Failure("email must not be null")
                : ((email.strip().length() == 0)
                ? new Failure("email must not be empty")
                : (EMAIL_PATTERN.matcher(email).matches()
                ? new Success(email)
                : new Failure("invalid email")));
    }

    interface Result<T> {

        void bind(final Consumer<T> success, final Consumer<T> failure);

        record Success(String email) implements Result<String> {
            @Override
            public void bind(Consumer<String> success, Consumer<String> failure) {
                success.accept(email);
            }
        }

        record Failure(String errorMessage) implements Result<String> {
            @Override
            public void bind(final Consumer<String> success, final Consumer<String> failure) {
                failure.accept(errorMessage);
            }
        }
    }

    public static void main(String[] args) {
        new EmailValidatorFunctional().apply("john.doe@acme.com").bind(sendVerificationMail(), logError());
        new EmailValidatorFunctional().apply("  ").bind(sendVerificationMail(), logError());
        new EmailValidatorFunctional().apply(null).bind(sendVerificationMail(), logError());
    }

    private static Consumer<String> sendVerificationMail() {
        return email -> out.println("Verification mail sent to " + email);
    }

    private static Consumer<String> logError() {
        return message -> err.println("Error message logged " + message);
    }
}
