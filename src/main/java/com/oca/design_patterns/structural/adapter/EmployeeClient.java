package com.oca.design_patterns.structural.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Advantages:
 * Deals with legacy code
 * Pitfalls:
 * Does not add extra functionality, otherwise it becomes decorator
 */
public class EmployeeClient {
    private List<Employee> employeeList;

    public List<Employee> getEmployeeList() {
        employeeList = new ArrayList<>();
        Employee employeeFromDB = new EmployeeDB("1", "Favio", "Tejada", "favio@gmail.com");
        employeeList.add(employeeFromDB);

        EmployeeLDAP employeeLDAP = new EmployeeLDAP("2", "Peter", "pan", "asd@gmail.com");
        employeeList.add(new EmployeeAdapterLdap(employeeLDAP));


        EmployeeCSV employeeCSV = new EmployeeCSV(1, "asd", "asd", "123");
        employeeList.add(new EmployeeAdapterCSV(employeeCSV));

        return employeeList;
    }
}
