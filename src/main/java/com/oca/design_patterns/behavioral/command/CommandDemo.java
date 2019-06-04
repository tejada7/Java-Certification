package com.oca.design_patterns.behavioral.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Advantages
 * Encapsulates each object as a request
 * Decouples sender from processor
 * Undo functionalities
 *
 * Pitfalls:
 * Multiple commands
 * Duplicating commands (chanin of responsability or composite)
 * Prototype dessing pattern for copies of commands
 * Reliance on other patterns
 */
public class CommandDemo {

    public static void main(String[] args) {
        Light bedroomlight = new Light();
        Light kitchenlight = new Light();
        Switch lightSwitch = new Switch();

//        Command onCommand = new OnCommand(light);
        Command toogleCommand = new ToogleCommand(bedroomlight);

        lightSwitch.storeAndExecute(toogleCommand);
//        lightSwitch.storeAndExecute(toogleCommand);
//        lightSwitch.storeAndExecute(toogleCommand);

        List<Light> lights = new ArrayList<>();
        lights.add(kitchenlight);
        lights.add(bedroomlight);

        Command allLightCommand = new AllLightsCommand(lights);

        lightSwitch.storeAndExecute(allLightCommand);
    }
}
