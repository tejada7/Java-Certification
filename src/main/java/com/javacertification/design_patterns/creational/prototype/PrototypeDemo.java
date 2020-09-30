package com.javacertification.design_patterns.creational.prototype;

import java.util.Arrays;
import java.util.List;

/**
 * Advantages:
 * Unique instances
 *
 * Pitfalls:
 * Not well spread
 * Used with other patterns
 * Shallow to deep copy -> more complexity
 */
public class PrototypeDemo {

    public static void main(String[] args) {

        // Shallow copy
        List<String> parameters = Arrays.asList("user1", "user2", "user3");
        Record record = new Record();
        ShallowCopy statement1 = new ShallowCopy("Select * from user", parameters, record);
        System.out.println(statement1);

        // Cloning the statement
        ShallowCopy statement2 = statement1.clone();
        System.out.println(statement2);

        System.out.println(statement1.equals(statement2));
        System.out.println(statement1 == statement2);

        // Deep copy
        Registry registry = new Registry();
        Movie movie = (Movie) registry.createItem("Movie");
        movie.setTitle("Creational Patterns in Java");

        System.out.println(movie);
        System.out.println(movie.getRuntime());
        System.out.println(movie.getTitle());
        System.out.println(movie.getUrl());

        Movie anotherMovie = (Movie) registry.createItem("Movie");
        anotherMovie.setTitle("Another Movie");

        System.out.println(anotherMovie);
        System.out.println(anotherMovie.getRuntime());
        System.out.println(anotherMovie.getTitle());
        System.out.println(anotherMovie.getUrl());
    }
}
