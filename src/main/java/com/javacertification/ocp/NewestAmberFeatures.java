package com.javacertification.ocp;

public class NewestAmberFeatures {

    record Point(int x, int y) {
    }

    enum Color {RED, GREEN, BLUE}

    record ColoredPoint(Point p, Color c) {

    }

    public static void main(String[] args) {
        final var o = new ColoredPoint(null, Color.GREEN);
        priorJava21(o);
        postJava21(o);
    }

    private static void postJava21(final Object o) {
        switch (o) {
            case ColoredPoint(var point, var color)
                    when point != null -> {
                System.out.println("At horizontal " + point.x());
                printColor(color);
            }
            case null, default -> doNothing();
        }
    }

    private static void doNothing() {

    }

    private static void printColor(final Color color) {
        switch (color) {
            case RED, BLUE, GREEN -> System.out.println("Color: " + color);
            case null -> System.out.println("Missing color");
        }
    }

    private static void priorJava21(final Object o) {
        if (o instanceof ColoredPoint) {
            ColoredPoint cp = (ColoredPoint) o;
            Point p = cp.p();
            if (p == null) {
                return;
            }
            int x = p.x();
            Color c = cp.c();
            System.out.println("At horizontal " + x);
            System.out.println("Color: " + c);
        }
    }
}
