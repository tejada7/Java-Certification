package com.javacertification.ocp;

import java.util.HashMap;

/**
 * To enable assert functionality, run this class with -ea option.
 */
public class MapApi {

    public static void main(String[] args) {
        // Method merge
        scenario1();

        scenario2();

        scenario3();

        scenario4();
    }

    private static void scenario4() {
        // Scenario 4:
        // If the requested key is not present in the map:
        var map = new HashMap<Integer, String>();
        map.put(1, "first");

        // the mapping function returns null
        map.merge(2, "newValue", (olvValue, newValue) -> "Do something...");

        // then the key and value are removed from the map
        assert map.get(2).equals("newValue");
    }

    private static void scenario3() {
        // Scenario 3:
        // If the requested key has a non-null value in the map:
        var map = new HashMap<Integer, String>();
        map.put(1, "first");
        map.put(2, "second");

        // the mapping function returns a non-null value
        map.merge(1, "newValue", String::concat);

        // then the key's value is the function's result
        assert map.get(1).equals("firstnewValue");
    }

    private static void scenario2() {
        // Scenario 2:
        // If the requested key has a non-null value in the map:
        var map = new HashMap<Integer, String>();
        map.put(1, "first");
        map.put(2, "second");

        // the mapping function returns null
        map.merge(1, "newValue", (olvValue, newValue) -> null);

        // then the key and value are removed from the map
        assert map.get(1) == null;
    }

    private static void scenario1() {
        // Scenario 1:
        // If the requested key has a null value in map:
        var map = new HashMap<Integer, String>();
        map.put(1, null);
        map.put(2, "second");

        // the mapping function is not called
        map.merge(1, "newValue", (olvValue, newValue) -> olvValue.length() + " " + newValue);

        // then the key's value is updated with the value parameter
        assert map.get(1).equals("newValue");
    }
}
