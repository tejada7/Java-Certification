package dessignPatterns.behavioral.mediator;

/**
 * This example works along with the command pattern
 * Advantages:
 * Define how objects interact with one another without having them refer to each other explicitly
 * Loose coupling
 * Reusable components
 * References only to the mediator
 * <p>
 * Pitfalls:
 * Deity object - everything to everybody
 * Limits subclassing
 * Confusing when to use it, specially against the command
 */
public class MediatorDemo {

    public static void main(String[] args) {
        Mediator mediator = new Mediator();
        Light bedroomlight = new Light("Beedrom");
        Light kitchenlight = new Light("Kitchen");

        mediator.registerLight(bedroomlight);
        mediator.registerLight(kitchenlight);
        Command turnOnAllLightsCommand = new TurnOnAllLightsCommand(mediator);

        turnOnAllLightsCommand.execute();

        Command turnOffAllLightsCommand = new TurnOffAllLightsCommand(mediator);

        turnOffAllLightsCommand.execute();
    }
}
