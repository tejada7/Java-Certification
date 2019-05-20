package com.oca.interviewquestions.tondeuse.business.impl;

import com.oca.interviewquestions.tondeuse.business.enums.Orientation;
import com.oca.interviewquestions.tondeuse.business.interfaces.Movable;
import com.oca.interviewquestions.tondeuse.model.Field;
import com.oca.interviewquestions.tondeuse.model.Mower;

import java.util.function.Consumer;

/**
 * Default implementation of the action move forward:
 */
public class MowerMovableImpl implements Movable {

    private Mower mower;
    private Consumer<String> consumer;

    /**
     * Instantiate a new object.
     *
     * @param mower          the model definition
     * @param loggerConsumer a consumer that defines the logging behavior
     */
    public MowerMovableImpl(Mower mower, Consumer<String> loggerConsumer) {
        this.mower = mower;
        this.consumer = loggerConsumer;
    }

    @Override
    public void execute(Orientation orientation) {
        consumer.accept(String.format("Moving from %s in %s direction", mower.getCurrentPosition(), orientation));
        switch (orientation) {
            case NORTH:
                if (mower.getCurrentPosition().posY + 1 < Field.maxSizeY) {
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
                if (mower.getCurrentPosition().posX + 1 < Field.maxSizeX) {
                    mower.getCurrentPosition().posX++;
                }
                break;
            default:
                //DO NOTHING
        }
        consumer.accept(String.format("The new Position is %s", mower.getCurrentPosition()));
    }
}
