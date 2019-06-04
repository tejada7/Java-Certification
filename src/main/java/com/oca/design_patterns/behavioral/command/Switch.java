package com.oca.design_patterns.behavioral.command;

/**
 * Invoker
 */
public class Switch {

    public void storeAndExecute(Command command) {
        command.execute();
    }
}
