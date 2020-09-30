package com.javacertification.interviewquestions.tondeuse.business.impl;

import com.javacertification.interviewquestions.tondeuse.business.enums.Action;
import com.javacertification.interviewquestions.tondeuse.business.enums.Orientation;
import com.javacertification.interviewquestions.tondeuse.business.interfaces.Actionable;
import com.javacertification.interviewquestions.tondeuse.business.interfaces.Movable;

import java.util.List;
import java.util.function.Consumer;

/**
 * Implementation of {@link Actionable} interface, structuring which methods to call and when based on: orientation and
 * action.
 */
public class MowerActionableImpl extends StringAbstractConsumer implements Actionable {


    /**
     * Instantiate a new object.
     *
     * @param loggerConsumer a loggerConsumer that defines the logging behavior
     */
    public MowerActionableImpl(Consumer<String> loggerConsumer) {
        super(loggerConsumer);
    }

    @Override
    public Orientation performActionBasedOnOrientation(Orientation currentOrientation, Action action, List<Movable> movablesImpl) {

        Orientation newOrientation = currentOrientation;
        switch (action) {
            case TURN_RIGHT:
                newOrientation = currentOrientation.turnRight();
                consumer.accept(String.format("Rotating to the %s", newOrientation));
                break;
            case TURN_LEFT:
                newOrientation = currentOrientation.turnLeft();
                consumer.accept(String.format("Rotating to the %s", newOrientation));
                break;
            case MOVE:
                movablesImpl.forEach(strategy -> strategy.moveForward(currentOrientation));
                break;
            default:
                // DO NOTHING
        }
        return newOrientation;
    }
}
