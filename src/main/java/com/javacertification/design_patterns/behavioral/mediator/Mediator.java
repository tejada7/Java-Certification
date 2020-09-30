package com.javacertification.design_patterns.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Favio on 10/12/2017.
 */
public class Mediator {

    private final List<Light> lights = new ArrayList<>();

    public void registerLight(Light light) {
        lights.add(light);
    }

    public void turnOnAllLights() {
        lights.stream()
                .filter(light -> !light.isOn())
                .forEach(Light::toggle);
    }

    public void turnOffAllLights() {
        lights.stream()
                .filter(Light::isOn)
                .forEach(Light::toggle);
    }
}
