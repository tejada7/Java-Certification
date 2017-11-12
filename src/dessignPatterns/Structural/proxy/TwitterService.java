package dessignPatterns.Structural.proxy;

/**
 * Created by Favio on 12/11/2017.
 */
public interface TwitterService {

    String getTimeline(String screenName);
    void postToTimeline(String screenName, String message);
}
