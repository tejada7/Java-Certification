package com.oca.designPatterns.creational.factory;

/**
 * Created by Favio on 11/11/2017.
 */
public class Shop extends Website {

    @Override
    protected void createWebsite() {
        pages.add(new CartPage());
        pages.add(new AboutPage());
        pages.add(new CommentPage());
    }
}
