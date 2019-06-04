package com.oca.design_patterns.behavioral.mediator;

/**
 * Created by Favio on 10/12/2017.
 */
public class TurnOffAllLightsCommand implements Command {

    private Mediator mediator;

    public TurnOffAllLightsCommand(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void execute() {
        mediator.turnOffAllLights();
    }
}
