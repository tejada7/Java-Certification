package com.javacertification.design_patterns.behavioral.command;

/**
 * Concrete command
 */
public class OnCommand implements Command {

    private Light light;

    public OnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
//        light.on();
    }
}
