package com.oca.designPatterns.behavioral.chainOfResponsibility;

/**
 * Advantages:
 * Loose coupling -> hierarchy
 *
 * Pitfalls:
 * Handling/Handler guarantee
 * Performance issues
 *
 */
public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        Pretar artanis = new Pretar();
        DarkTemplar zeratul = new DarkTemplar();
        HighTemplar tassadar = new HighTemplar();

        artanis.setSuccessor(zeratul);
        zeratul.setSuccessor(tassadar);

        Request request = new Request(RequestType.CONFERENCE, 500);
        artanis.handleRequest(request);

        request = new Request(RequestType.ATTACK, 1000);
        artanis.handleRequest(request);

        request = new Request(RequestType.ATTACK, 2000);
        artanis.handleRequest(request);
    }
}
