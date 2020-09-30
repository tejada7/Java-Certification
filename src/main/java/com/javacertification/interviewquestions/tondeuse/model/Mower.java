package com.javacertification.interviewquestions.tondeuse.model;

import com.javacertification.interviewquestions.tondeuse.MowerLauncher;
import com.javacertification.interviewquestions.tondeuse.business.enums.Action;
import com.javacertification.interviewquestions.tondeuse.business.enums.Orientation;
import com.javacertification.interviewquestions.tondeuse.business.impl.MowerActionableImpl;
import com.javacertification.interviewquestions.tondeuse.business.impl.MowerMovableImpl;
import com.javacertification.interviewquestions.tondeuse.business.interfaces.Actionable;
import com.javacertification.interviewquestions.tondeuse.business.interfaces.Movable;
import com.javacertification.interviewquestions.tondeuse.business.interfaces.Rotatable;
import com.javacertification.interviewquestions.tondeuse.view.Drawer;
import com.javacertification.interviewquestions.tondeuse.view.TextAreaHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contain the business logic of the mower problem described in {@link MowerLauncher}.
 */
public class Mower {

    private static final Logger LOGGER = Logger.getLogger(Mower.class.toString());

    static {
        LOGGER.addHandler(new TextAreaHandler(Drawer.textArea));
    }

    private Position currentPosition;
    private Orientation currentOrientation;
    private List<Movable> strategiesWhenMoving;
    private Rotatable strategyWhenRotating;
    private Actionable actionsPerformer;

    /**
     * Constructor with two arguments. Initializes the default {@link Movable} strategy in order to add the
     * expected functionality when calling the {@link Mower#performAction(Action)} method.
     *
     * @param initialPosition    the initial position of the mower on the grass field
     * @param initialOrientation the initial orientation
     * @see Orientation
     */
    public Mower(Position initialPosition, Orientation initialOrientation) {
        currentPosition = initialPosition;
        currentOrientation = initialOrientation;
        strategiesWhenMoving = new ArrayList<>();
        final Consumer<String> stringConsumer = s -> LOGGER.log(Level.INFO, s);
        MowerMovableImpl defaultMovableImpl = new MowerMovableImpl(this, stringConsumer);
        strategiesWhenMoving.add(defaultMovableImpl);
        actionsPerformer = new MowerActionableImpl(stringConsumer);
    }

    /**
     * Add extra optionals {@link Rotatable} and {@link Movable} implementations in order to perform multiple tasks at
     * the same time, when invoking {@link Mower#performAction(Action)}.
     *
     * @param initialPosition    the initial position of the mower on the grass field
     * @param initialOrientation the initial orientation
     * @param movableImpl        a {@link Movable} implementation
     * @param rotatableImpl      a {@link Rotatable} implementation
     * @see Mower#Mower(Position, Orientation)
     */
    public Mower(Position initialPosition, Orientation initialOrientation, Movable movableImpl, Rotatable rotatableImpl) {
        this(initialPosition, initialOrientation);
        strategiesWhenMoving.add(movableImpl);
        strategyWhenRotating = rotatableImpl;
    }

    /**
     * Perform on of the actions defined in {@link Action}, based on the {@link Mower#currentOrientation}.
     * It also adds the calls the consumer implementations to perform various tasks.
     *
     * @param action the action to perform
     */
    public void performAction(Action action) {
        currentOrientation = Objects.isNull(strategyWhenRotating) ?
                actionsPerformer.performActionBasedOnOrientation(currentOrientation, action, strategiesWhenMoving) :
                actionsPerformer.performActionsAndGetNewOperation(currentOrientation, action, strategiesWhenMoving, strategyWhenRotating);
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public Orientation getCurrentOrientation() {
        return currentOrientation;
    }

    @Override
    public String toString() {
        return currentPosition + " " + currentOrientation.getAbbreviation();
    }
}
