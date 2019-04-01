package com.oca.designPatterns.behavioral.command;

/**
 * Created by Favio on 10/12/2017.
 */
public class ToogleCommand implements Command {

    private Light light;

    public ToogleCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.toggle();
    }
}
