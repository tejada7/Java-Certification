package com.oca.designPatterns.Structural.adapter;

/**
 * Created by Favio on 11/11/2017.
 */
public class EmployeeAdapterLdap implements Employee {

    private EmployeeLDAP instance;

    public EmployeeAdapterLdap(EmployeeLDAP instance) {
        this.instance = instance;
    }

    @Override
    public String getId() {
        return instance.getCn();
    }

    @Override
    public String getFirstName() {
        return instance.getSurName();
    }

    @Override
    public String getLastName() {
        return instance.getGivenName();
    }

    @Override
    public String getEmail() {
        return instance.getMail();
    }

    public String toString() {
        return "ID: " + instance.getCn();
    }
}
