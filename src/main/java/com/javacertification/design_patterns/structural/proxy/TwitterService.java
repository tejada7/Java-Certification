package com.javacertification.design_patterns.structural.proxy;

/**
 * Created by Favio on 12/11/2017.
 */
public interface TwitterService {

    String getTimeline(String screenName);
    void postToTimeline(String screenName, String message);
}
