package com.javacertification.interviewquestions.tondeuse.business.enums;

import com.javacertification.interviewquestions.tondeuse.business.interfaces.Rotatable;
import com.javacertification.interviewquestions.tondeuse.exceptions.InvalidActionException;
import com.javacertification.interviewquestions.tondeuse.view.Drawer;
import com.javacertification.interviewquestions.tondeuse.view.TextAreaHandler;

import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

/**
 * Represent the 4 cardinal positions of the lawnmower, along with the business logic when turning in different
 * directions.
 */
public enum Orientation implements Rotatable {
    NORTH("N", "mowerN.png") {
        @Override
        public Orientation turnRight() {
            return EAST;
        }

        @Override
        public Orientation turnLeft() {
            return WEST;
        }
    }, SOUTH("S", "mowerS.png") {
        @Override
        public Orientation turnRight() {
            return WEST;
        }

        @Override
        public Orientation turnLeft() {
            return EAST;
        }
    }, WEST("W", "mowerW.png") {
        @Override
        public Orientation turnRight() {
            return NORTH;
        }

        @Override
        public Orientation turnLeft() {
            return SOUTH;
        }
    }, EAST("E", "mowerE.png") {
        @Override
        public Orientation turnRight() {
            return SOUTH;
        }

        @Override
        public Orientation turnLeft() {
            return NORTH;
        }
    };

    private String abbreviation;
    private String imgPath;
    private static Map<String, Orientation> positionByAbbreviation = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(Orientation.class.toString());

    static {
        LOGGER.addHandler(new TextAreaHandler(Drawer.textArea));
        Arrays.stream(Orientation.values()).forEach(item -> positionByAbbreviation.put(item.abbreviation, item));
    }

    Orientation(String abbreviation, String imgPath) {
        this.abbreviation = abbreviation;
        this.imgPath = imgPath;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * Retrieve the enum constant given its abbreviation.
     *
     * @param abbreviation a {@link String} abbreviation representing the orientation
     * @return a {@link Orientation} constant
     * @throws InvalidActionException if no abbreviation matches to the stored ones
     */
    public static Orientation getOrientationByAbbreviation(String abbreviation) throws InvalidActionException {
        return Optional.of(positionByAbbreviation.get(abbreviation.toUpperCase())).orElseThrow(
                () -> new InvalidActionException("The action " + abbreviation + " is invalid.")
        );
    }

    /**
     * Return the URL containing the image representing orientation.
     *
     * @return an {@link URL} object
     */
    public URL getImageURL() {
        return Objects.requireNonNull(this.getClass().getClassLoader().getResource(imgPath));
    }
}
