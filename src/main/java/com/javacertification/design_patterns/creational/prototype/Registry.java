package com.javacertification.design_patterns.creational.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Favio on 11/11/2017.
 */
public class Registry {
    private Map<String, Item> items = new HashMap<>();

    public Registry() {
        loadItems();
    }

    public Item createItem(String type) {
        Item item = null;
        try {
            item = (Item) items.get(type).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return item;
    }

    private void loadItems() {
        Movie movie = new Movie();
        movie.setTitle("Movie Title");
        movie.setPrice(24.99d);
        movie.setRuntime("2 hours");
        items.put("Movie", movie);

        Book book = new Book();
        book.setNumberOfPages(200);
        book.setTitle("Book Title");
        book.setPrice(25d);
        items.put("Book", book);
    }
}
