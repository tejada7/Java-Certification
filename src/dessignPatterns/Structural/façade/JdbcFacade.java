package dessignPatterns.Structural.façade;

import dessignPatterns.creational.singleton.DBSingleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Favio on 12/11/2017.
 */
public class JdbcFacade {

    DBSingleton instance = null;

    public JdbcFacade() {
        instance = DBSingleton.getInstance();
    }

    public int createTable() {
        int count = 0;
        try {
            Connection conn = instance.getConnection();
            Statement sta = conn.createStatement();
            count = sta.executeUpdate("CREATE TABLE Address (ID INTEGER, StreetName VARCHAR(50))");
            sta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int insertIntoTable() {
        int count = 0;
        try {
            Connection conn = instance.getConnection();
            Statement sta = conn.createStatement();
            count = sta.executeUpdate("INSERT INTO Address (ID INTEGER, StreetName VARCHAR(50)) VALUES (2250, 'Reseguin')");
            sta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<Address> getAddresses() {
        List<Address> addresses = new ArrayList<>();
        try {
            Connection conn = instance.getConnection();
            Statement sta = conn.createStatement();
            ResultSet rs = sta.executeQuery("SELECT  * FROM  Address");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
                addresses.add(new Address(rs.getString(1), rs.getString(2), rs.getString(3)));
            }

            sta.executeUpdate("INSERT INTO Address (ID INTEGER, StreetName VARCHAR(50)) VALUES (2250, 'Reseguin')");
            sta.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addresses;
    }
}

class Address {
    private String id;
    private String streetName;
    private String city;

    public Address(String id, String streetName, String city) {
        this.id = id;
        this.streetName = streetName;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}