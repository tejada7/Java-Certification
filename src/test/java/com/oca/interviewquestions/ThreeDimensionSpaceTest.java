package com.oca.interviewquestions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

import static com.oca.interviewquestions.ThreeDimensionSpace.Point3D;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ThreeDimensionSpaceTest {

    @Parameters
    public static Collection data() {
        return asList(new Object[][]{
                {new Point3D(1, 2, 3), new Point3D(4, 6, 8), 7.07},
                {new Point3D(0, 0, 0), null, Double.NaN},
                {null, new Point3D(0, 0, 0), Double.NaN},
                {new Point3D(4, 2, 2), new Point3D(2, 2, 2), 2.0},
                {new Point3D(5, 2, 8), new Point3D(10, 5, 7), 5.92}
        });
    }

    @Parameter(value = 0)
    public Point3D p1;

    @Parameter(value = 1)
    public Point3D p2;

    @Parameter(value = 2)
    public double expected;

    @Test
    public void calculateDistance() {
        assertEquals(expected, ThreeDimensionSpace.distance(p1, p2), 0.0);
    }
}