package com.javacertification.ocp.patternmatching;

import java.util.Comparator;

public class ReassingmentDemo implements Comparator<Number> {

    @Override
    public int compare(final Number o1, final Number o2) {
        if (o1 instanceof Integer integer1 && o2 instanceof Integer integer2) {
            return integer1.compareTo(integer2);
        }
        if (o1 instanceof Float float1 && o2 instanceof Float float2) {
            return float1.compareTo(float2);
        }
        if (o1 instanceof Double double1 && o2 instanceof Double double2) {
            return double1.compareTo(double2);
        }
        if (o1 instanceof Long long1 && o2 instanceof Long long2) {
            return long1.compareTo(long2);
        }
        return -1;
    }
}
