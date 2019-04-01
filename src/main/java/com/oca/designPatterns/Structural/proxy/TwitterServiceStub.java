package com.oca.designPatterns.Structural.proxy;

/**
 * Created by Favio on 12/11/2017.
 */
public class TwitterServiceStub implements TwitterService {

    @Override
    public String getTimeline(String screenName) {
        return "timeline";
    }

    @Override
    public void postToTimeline(String screenName, String message) {

    }
}
