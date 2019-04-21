package com.oca.interviewquestions;

/**
 * Class that contains helpful methods to calculate distance between 2 points in space.
 *
 * @author Favio
 */
public class ThreeDimensionSpace {

    private ThreeDimensionSpace() {
        throw new IllegalStateException("Constructor not allowed for this class.");
    }

    public static class Point {
        protected double x;
        protected double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }
    }

    public static class Point3D extends Point {
        protected double z;

        public Point3D(double x, double y, double z) {
            super(x, y);
            this.z = z;
        }

        public double getZ() {
            return z;
        }
    }

    /**
     * Calculate the distance between 2 points in space.
     * This method uses the function d=sqrt[(x1 - y1)^2 + (x2 - y2)^2 + (x3 - y3)^2].
     *
     * @param p1 the first point in space
     * @param p2 the second point in space
     * @return the distance in <code>double<code/>
     */
    public static double distance(Point3D p1, Point3D p2) {
        if (null != p1 && null != p2) {
            return Math.round(Math.sqrt(calculateDiffSquare(p1.x, p2.x) + calculateDiffSquare(p1.y, p2.y) + calculateDiffSquare(p1.z, p2.z)) * 100d) / 100d;
        }
        return Double.NaN;
    }

    private static double calculateDiffSquare(double x, double x1) {
        return Math.pow(x - x1, 2);
    }
}
