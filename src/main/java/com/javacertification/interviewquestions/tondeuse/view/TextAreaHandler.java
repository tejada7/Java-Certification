package com.javacertification.interviewquestions.tondeuse.view;

import javax.swing.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * A handler that allows to pass the logging content onto the {@link Drawer#textArea} component synchronously.
 */
public class TextAreaHandler extends Handler {

    private JTextArea textArea;

    /**
     * Attach the UI component.
     *
     * @param textArea a {@link JTextArea} Object
     */
    public TextAreaHandler(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void publish(final LogRecord record) {
        SwingUtilities.invokeLater(() -> {
            StringWriter text = new StringWriter();
            PrintWriter out = new PrintWriter(text);
            out.println(textArea.getText());
            out.printf("> %s", record.getMessage());
            textArea.setText(text.toString());
        });
    }

    @Override
    public void flush() {
        // DO NOTHING
    }

    @Override
    public void close() throws SecurityException {
        // DO NOTHING
    }
}
