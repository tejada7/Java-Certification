package dessignPatterns.Structural.adapter;

import java.util.List;

/**
 * Created by Favio on 11/11/2017.
 */
public class AdapterDemo {
    public static void main(String[] args) {
        EmployeeClient client = new EmployeeClient();
        List<Employee> employees = client.getEmployeeList();
        System.out.println(employees);
    }
}
