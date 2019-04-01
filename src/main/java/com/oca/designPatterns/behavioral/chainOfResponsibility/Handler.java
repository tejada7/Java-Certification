package com.oca.designPatterns.behavioral.chainOfResponsibility;

/**
 * Created by Favio on 12/11/2017.
 */
public abstract class Handler {

    protected Handler successor;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public abstract void handleRequest(Request request);
}
