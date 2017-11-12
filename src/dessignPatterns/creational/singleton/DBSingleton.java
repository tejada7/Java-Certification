package dessignPatterns.creational.singleton;

import java.sql.Connection;

/**
 * Lazy loaded and thread safe singleton class.
 */
public class DBSingleton {
    private static DBSingleton instance;

    private DBSingleton() {
    }

    /**
     * To make sure that the object is instantiated at runtime, add the
     * if sentence and initialize the class variable to null (null).
     * To ma it thread safe, then add the synchronize.
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
        return null;
    }
}
