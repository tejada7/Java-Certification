package com.oca.design_patterns.structural.composite;

/**
 * Created by Favio on 12/11/2017.
 */
public class MenuItem extends MenuComponent {

    public MenuItem(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public String toString() {
        return print(this);
    }
}
