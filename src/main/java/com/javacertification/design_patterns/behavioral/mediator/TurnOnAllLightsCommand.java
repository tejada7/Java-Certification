package com.javacertification.design_patterns.behavioral.mediator;

/**
 * Created by Favio on 10/12/2017.
 */
public class TurnOnAllLightsCommand implements Command {

    private Mediator mediator;

    public TurnOnAllLightsCommand(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void execute() {
        mediator.turnOnAllLights();
    }
}
