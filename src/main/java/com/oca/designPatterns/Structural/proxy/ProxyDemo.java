package com.oca.designPatterns.Structural.proxy;

/**
 * Advantages:
 * Its main purpose is to control invocations at compile time
 * Pitfalls:
 * Only one proxy per object
 * Adds another abstraction layer
 */
public class ProxyDemo {

    public static void main(String[] args) {
        TwitterService service = (TwitterService) SecurityProxy.newInstance(new TwitterServiceStub());
        System.out.println(service.getTimeline("screenName"));
        service.postToTimeline("screenName", "message");
    }
}
