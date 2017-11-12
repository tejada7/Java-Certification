package dessignPatterns.creational.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Favio on 11/11/2017.
 */
public abstract class Website {
    protected List<Page> pages = new ArrayList<>();

    public List<Page> getPages() {
        return pages;
    }

    public Website() {
        createWebsite();
    }

    protected abstract void createWebsite();
}
