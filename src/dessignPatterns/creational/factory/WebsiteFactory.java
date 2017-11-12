package dessignPatterns.creational.factory;

/**
 * Created by Favio on 11/11/2017.
 */
public class WebsiteFactory {

    public static Website getWebsite(SiteType siteType) {
        switch (siteType) {
            case BLOG:
                return new Blog();
            case SHOP:
                return new Shop();
            default:
                return null;
        }
    }
}
