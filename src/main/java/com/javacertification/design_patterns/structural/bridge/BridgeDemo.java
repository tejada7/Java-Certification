package com.javacertification.design_patterns.structural.bridge;

/**
 * Advantages:
 * Every component can change independently of one another
 * Design upfront -> built in advance
 * Pitfalls:
 * Increases Complexity (look for what makes sense to abstract out)
 * More than just OO
 * Confusion in determining what goes where
 */
public class BridgeDemo {
    public static void main(String[] args) {
        Movie movie = new Movie("action", "2:15", "John Wick", "2014");

        Formatter printFormatter = new PrintFormatter();

        Printer moviePrinter = new MoviePrinter(movie);

        String printMateriel = moviePrinter.print(printFormatter);

        System.out.println(printMateriel);

        // Another formatter
        Formatter htmlFormatter = new HtmlFormatter();

        String htmlMateriel = moviePrinter.print(htmlFormatter);

        System.out.println(htmlMateriel);
    }
}
