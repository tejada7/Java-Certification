package com.oca.designPatterns.behavioral.iterator;

import java.util.Iterator;

/**
 * Ideally the generic should be replaced by a Bike class
 */
public class BikeRepository implements Iterable<String> {

    private String[] bikes;
    private int index;

    public BikeRepository() {
        bikes = new String[10];
    }

    public void addBike(String bike) {
        if (index == bikes.length) {
            String[] largerBikes = new String[bikes.length + 5];
            System.arraycopy(bikes, 0, largerBikes, 0, bikes.length);
            bikes = largerBikes;
            largerBikes = null;
        }

        bikes[index++] = bike;
    }

//    @NotNull
    @Override
    public Iterator<String> iterator() {
        Iterator<String> it = new Iterator<String>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < bikes.length
                        && bikes[currentIndex] != null;
            }

            @Override
            public String next() {
                return bikes[currentIndex++];
            }

            @Override
            public void remove() {
               // TODO: set element to null and resize the array's size
                throw new UnsupportedOperationException("Not implemented yet");
            }
        };
        return it;
    }
}
