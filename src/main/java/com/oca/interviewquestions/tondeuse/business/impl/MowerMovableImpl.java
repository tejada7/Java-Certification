package com.oca.interviewquestions.tondeuse.business.impl;

import com.oca.interviewquestions.ThreeDimensionSpace.Point;
import com.oca.interviewquestions.tondeuse.business.enums.Orientation;
import com.oca.interviewquestions.tondeuse.business.interfaces.Movable;
import com.oca.interviewquestions.tondeuse.model.Field;
import com.oca.interviewquestions.tondeuse.model.Mower;

import java.util.function.Consumer;

/**
 * Default implementation of the action move forward: update the positions based on a rectangular area of size
 * ( ({@link Field#maxSizeX} + 1) * (({@link Field#maxSizeY} + 1)) ), where the left bottom corner represents the point
 * {@link Point} (0, 0).
 *
 */
public class MowerMovableImpl extends StringAbstractConsumer implements Movable {

    private Mower mower;

    /**
     * Instantiate a new object.
     *
     * @param mower          the model definition
     * @param loggerConsumer a consumer that defines the logging behavior
     */
    public MowerMovableImpl(Mower mower, Consumer<String> loggerConsumer) {
        super(loggerConsumer);
        this.mower = mower;
    }

    @Override
    public void moveForward(Orientation orientation) {
        consumer.accept(String.format("Moving from %s in %s direction", mower.getCurrentPosition(), orientation));
        switch (orientation) {
            case NORTH:
                if (mower.getCurrentPosition().posY + 1 <= Field.maxSizeY) {
                    mower.getCurrentPosition().posY++;
                }
                break;
            case SOUTH:
                if (mower.getCurrentPosition().posY - 1 >= 0) {
                    mower.getCurrentPosition().posY--;
                }
                break;
            case WEST:
                if (mower.getCurrentPosition().posX - 1 >= 0) {
                    mower.getCurrentPosition().posX--;
                }
                break;
            case EAST:
                if (mower.getCurrentPosition().posX + 1 <= Field.maxSizeX) {
                    mower.getCurrentPosition().posX++;
                }
                break;
            default:
                //DO NOTHING
        }
        consumer.accept(String.format("The new Position is %s", mower.getCurrentPosition()));
    }
}
