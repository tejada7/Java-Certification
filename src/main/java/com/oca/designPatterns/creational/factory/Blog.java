package com.oca.designPatterns.creational.factory;

/**
 * Created by Favio on 11/11/2017.
 */
public class Blog extends Website {

    @Override
    protected void createWebsite() {
        pages.add(new PostPage());
        pages.add(new AboutPage());
        pages.add(new CommentPage());
        pages.add(new ContactPage());
    }
}
