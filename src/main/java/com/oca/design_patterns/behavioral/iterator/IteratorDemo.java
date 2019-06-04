package com.oca.design_patterns.behavioral.iterator;

import java.util.*;

/**
 * Advantages:
 * Decouples data from algorithms to retrieve data
 * Mainly used when changing Collection implementation e.g. List to Set
 * Concurrent modification
 *
 * Pitfalls:
 * No access to any index
 * Unidirectional (incremental)
 * Slower in some cases than compare with direct index access
 */
public class IteratorDemo {

    public static void main(String[] args) {
        BikeRepository repo = new BikeRepository();
        repo.addBike("Cervelo");
        repo.addBike("Scott");
        repo.addBike("Fuji");

        Iterator<String> bikeIteraror = repo.iterator();

        while (bikeIteraror.hasNext()) {
            System.out.println(bikeIteraror.next());
        }

        for (String bike: repo) {
            System.out.println(bike);
        }
    }
}
