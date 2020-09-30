package com.javacertification.design_patterns.creational.singleton;

import java.sql.Connection;

/**
 * Lazy loaded and thread safe singleton class.
 */
public class DBSingleton implements AutoCloseable {
    private static DBSingleton instance;

    private DBSingleton() {
    }

    /**
     * To make sure that the object is instantiated at runtime, add the
     * if sentence and initialize the class variable to null (null).
     * To make it thread safe, then add the synchronize.
     *
     * @return the single instance of the class {@link DBSingleton}
     */
    public static DBSingleton getInstance() {
        if (instance == null) {
            synchronized (DBSingleton.class) {
                instance = new DBSingleton();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        // Not Implemented for exemplary purpose
        return null;
    }

    @Override
    public void close() throws Exception {
        // Not Implemented for exemplary purpose
    }
}
