package com.javacertification.functionalprogramming;

import java.util.regex.Pattern;

public class EmailValidatorImperative {
    private final Pattern emailPattern = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");

    void testEmail(final String email) {
        if (emailPattern.matcher(email).matches()) {
            sendVerificationMail(email);
        } else {
            logError("email " + email + " is invalid.");
        }
    }

    private void sendVerificationMail(final String email) {
        System.out.println("Verification mail sent to " + email);
    }

    private void logError(final String message) {
        System.err.println("Error message logged " + message);
    }
}
