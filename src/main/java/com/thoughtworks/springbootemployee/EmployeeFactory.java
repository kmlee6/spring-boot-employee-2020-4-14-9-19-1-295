package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeFactory {
    public static List<Employee> createTestEmployees() {
        List<Employee> employee = new ArrayList<>();
        employee.add(new Employee(1, "Tom", 22, "Male", 50000));
        employee.add(new Employee(2, "Mary", 18, "Female", 60000));
        employee.add(new Employee(3, "Kelvin", 16, "Male", 70000));
        return employee;
    }
}
