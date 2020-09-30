package com.javacertification.design_patterns.creational.factory;

/**
 * Advantages:
 *
 * Pitfalls:
 * Complexity
 * Doble the amount of code
 */
public class FactoryDemo {

    public static void main(String[] args) {
        Website website = WebsiteFactory.getWebsite(SiteType.BLOG);
        System.out.println(website.getPages());

        website = WebsiteFactory.getWebsite(SiteType.SHOP);
        System.out.println(website.getPages());
    }
}
