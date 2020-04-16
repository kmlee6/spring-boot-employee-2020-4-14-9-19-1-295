package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeFactory {
    public static List<Employee> createTestEmployees() {
        List<Employee> employee = new ArrayList<>();
        employee.add(new Employee(1, "Tom", 22, "Male", 5000));
        employee.add(new Employee(2, "Mary", 18, "Female", 6000));
        employee.add(new Employee(23, "Kelvin", 16, "Male", 7000));
        return employee;
    }
}
