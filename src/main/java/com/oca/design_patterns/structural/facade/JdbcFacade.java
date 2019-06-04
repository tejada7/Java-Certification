package com.oca.design_patterns.structural.facade;

import com.oca.design_patterns.creational.singleton.DBSingleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Favio on 12/11/2017.
 */
public class JdbcFacade {

    private DBSingleton instance;
    private static final Logger LOGGER = Logger.getLogger(JdbcFacade.class.toString());
    private static final String GENERIC_SQL_EXCEPTION_MESSAGE = "An exception was thrown : {0}.";

    public JdbcFacade() {
        instance = DBSingleton.getInstance();
    }

    /**
     * Create a table named Address.
     *
     * @return the database return code
     */
    public int createTable() {
        int count = 0;
        try (Connection conn = instance.getConnection();
             Statement sta = conn.createStatement()) {
            count = sta.executeUpdate("CREATE TABLE Address (ID INTEGER, StreetName VARCHAR(50))");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, GENERIC_SQL_EXCEPTION_MESSAGE, e.getSQLState());
        }
        return count;
    }

    /**
     * Insert a record to the table Address.
     *
     * @return the database return code
     */
    public int insertIntoTable() {
        int count = 0;
        try (Connection conn = instance.getConnection();
             Statement sta = conn.createStatement()) {
            count = sta.executeUpdate("INSERT INTO Address (ID INTEGER, StreetName VARCHAR(50)) VALUES (2250, 'Reseguin')");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, GENERIC_SQL_EXCEPTION_MESSAGE, e.getSQLState());
        }
        return count;
    }

    /**
     * Retrieve a list of Addresses from the database.
     *
     * @return a list of objects {@link Address}
     */
    public List<Address> getAddresses() {
        List<Address> addresses = new ArrayList<>();
        try (Connection conn = instance.getConnection();
             Statement sta = conn.createStatement()) {
            try (ResultSet rs = sta.executeQuery("SELECT  * FROM  Address")) {
                while (rs.next()) {
                    System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
                    addresses.add(new Address(rs.getString(1), rs.getString(2), rs.getString(3)));
                }
            }
            sta.executeUpdate("INSERT INTO Address (ID INTEGER, StreetName VARCHAR(50)) VALUES (2250, 'Reseguin')");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, GENERIC_SQL_EXCEPTION_MESSAGE, e.getSQLState());
        }
        return addresses;
    }
}

class Address {

    Address(String id, String streetName, String city) {
        // Initialization of the fields...
    }
}
