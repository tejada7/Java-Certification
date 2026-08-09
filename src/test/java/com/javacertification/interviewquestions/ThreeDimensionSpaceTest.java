package com.javacertification.interviewquestions;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.javacertification.interviewquestions.ThreeDimensionSpace.Point3D;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ThreeDimensionSpaceTest {

    private static Stream<Arguments> data() {
        return Stream.of(
            Arguments.of(new Point3D(1, 2, 3), new Point3D(4, 6, 8), 7.07),
            Arguments.of(new Point3D(0, 0, 0), null, Double.NaN),
            Arguments.of(null, new Point3D(0, 0, 0), Double.NaN),
            Arguments.of(new Point3D(4, 2, 2), new Point3D(2, 2, 2), 2.0),
            Arguments.of(new Point3D(5, 2, 8), new Point3D(10, 5, 7), 5.92));
    }

    @ParameterizedTest
    @MethodSource("data")
    public void calculateDistance(Point3D p1, Point3D p2, double expected) {
        assertEquals(expected, ThreeDimensionSpace.distance(p1, p2), 0.0);
    }
}
