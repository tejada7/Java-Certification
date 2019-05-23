package com.oca.interviewquestions.tondeuse.model;

/**
 * Model class representing a point in the Cartesian plane.
 */
public class Position {
    public int posX;
    public int posY;

    /**
     * Constructor that initializes the point in both dimensions.
     *
     * @param posX the x position
     * @param posY the y position
     */
    public Position(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Overloaded constructor that allow parameters as {@link String} objects.
     *
     * @param posX the x position
     * @param posY the y position
     */
    public Position(String posX, String posY) {
        this(Integer.parseInt(posX), Integer.parseInt(posY));
    }

    @Override
    public String toString() {
        return posX + " " + posY;
    }
}
