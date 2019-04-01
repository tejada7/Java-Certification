package com.oca.designPatterns.behavioral.chainOfResponsibility;

/**
 * Created by Favio on 12/11/2017.
 */
public class Pretar extends Handler {

    @Override
    public void handleRequest(Request request) {
        if (request.getRequestType() == RequestType.CONFERENCE) {
            System.out.println("Pretars can hold conferences");
        } else {
            successor.handleRequest(request);
        }
    }
}
