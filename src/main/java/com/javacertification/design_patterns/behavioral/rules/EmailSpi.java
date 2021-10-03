package com.javacertification.design_patterns.behavioral.rules;

import static java.lang.System.lineSeparator;
import static java.lang.System.out;

public interface EmailSpi {
    void sendEmail(String recipient, String content);

    static EmailSpi defaultSender() {
        return (recipient, content) -> out.println(new StringBuilder("Recipient: ")
                .append(recipient)
                .append(lineSeparator())
                .append("Content: ")
                .append(content));
    }
}
