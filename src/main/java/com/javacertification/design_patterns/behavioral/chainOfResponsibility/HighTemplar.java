package com.javacertification.design_patterns.behavioral.chainOfResponsibility;

/**
 * Created by Favio on 12/11/2017.
 */
public class HighTemplar extends Handler {

    @Override
    public void handleRequest(Request request) {
        System.out.println("High Templars can approve anything they want");
    }
}
