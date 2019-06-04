package com.oca.design_patterns.behavioral.command;

import java.util.List;

/**
 * Created by Favio on 10/12/2017.
 */
public class AllLightsCommand implements Command {

    private List<Light> lights;

    public AllLightsCommand(List<Light> lights) {
        this.lights = lights;
    }

    @Override
    public void execute() {
        lights.stream().filter(light -> light.isOn())
                .forEach(e -> e.toggle());
    }
}
