package com.oca.design_patterns.behavioral.chainOfResponsibility;

/**
 * Created by Favio on 12/11/2017.
 */
public class DarkTemplar extends Handler {

    @Override
    public void handleRequest(Request request) {
        if (request.getRequestType() == RequestType.ATTACK) {
            if (request.getAmount() < 1500) {
                System.out.println("Dark Templar can lead attacks");
            } else {
                successor.handleRequest(request);
            }
        }
    }
}
