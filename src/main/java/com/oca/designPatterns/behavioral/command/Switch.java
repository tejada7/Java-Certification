package com.oca.designPatterns.behavioral.command;

/**
 * Invoker
 */
public class Switch {

    public void storeAndExecute(Command command) {
        command.execute();
    }
}
