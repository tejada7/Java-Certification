package com.oca.interviewquestions.tondeuse.business.interfaces;

import com.oca.interviewquestions.tondeuse.business.enums.Action;
import com.oca.interviewquestions.tondeuse.business.enums.Orientation;

import java.util.List;

/**
 * Define the behavior to apply the business rules when performing an action based on an orientation.
 */
public interface Actionable {

    /**
     * Return the new orientation based on a given action. It additionally executes the implementation of a
     * {@link Movable} interface.
     *
     * @param action       the type of action
     * @param movablesImpl the functions to be executed for a {@link Movable} implementation
     * @return the new orientation based on the rules depicted in the methods {@link Orientation#turnRight()} and
     * {@link Orientation#turnLeft()}
     */
    Orientation performActionBasedOnOrientation(Orientation currentOrientation, Action action, List<Movable> movablesImpl);

    /**
     * Launch a set of actions defined in the {@link Movable} and {@link Rotatable} implementation, in order to retrieve
     * the new orientation.
     * This method abstraction is essential for defining the business rules to apply to a given solution.
     *
     * @param currentOrientation the current orientation of the mower
     * @param actionToPerform    the action to perform
     * @param movablesImpl       the functions to be executed for a {@link Movable} implementation
     * @param rotatableImpl      the functions to be executed for a {@link Rotatable} implementation
     * @return the new orientation based on the rules depicted in the methods {@link Orientation#turnRight()} and
     * {@link Orientation#turnLeft()}
     */
    default Orientation performActionsAndGetNewOperation(Orientation currentOrientation, Action actionToPerform,
                                                         List<Movable> movablesImpl, Rotatable rotatableImpl) {
        switch (actionToPerform) {
            case TURN_RIGHT:
                rotatableImpl.turnRight();
                break;
            case TURN_LEFT:
                rotatableImpl.turnLeft();
                break;
            default:
                // DO NOTHING
        }
        return performActionBasedOnOrientation(currentOrientation, actionToPerform, movablesImpl);
    }
}
