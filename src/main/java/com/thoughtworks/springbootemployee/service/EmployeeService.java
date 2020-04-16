package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.thoughtworks.springbootemployee.EmployeeFactory.createTestEmployees;

@Service
public class EmployeeService {
    public List<Employee> getEmployeesByGender(String gender) {
        return createTestEmployees()
                .stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeesByPage(Integer page, Integer pageSize) {

        int firstIndex = page * pageSize - 1;
        int lastIndex = (page + 1) * pageSize - 1;
        return createTestEmployees().subList(firstIndex, lastIndex);
    }

    public List<Employee> getAllEmployees() {
        return createTestEmployees();
    }

    public Employee getEmployeesById(int employeeId) {
        return createTestEmployees()
                .stream()
                .filter(employee -> employee.getId() == employeeId)
                .findFirst()
                .orElse(null);
    }

    public Employee addEmployee(Employee newEmployee) {
        List<Employee> employeeList = createTestEmployees();
        employeeList.add(newEmployee);
        return newEmployee;
    }

    public Employee updateEmployInfo(int employeeId, Employee targetEmployee) {
        List<Employee> employeeList = createTestEmployees();
        employeeList.removeIf(employee -> employee.getId() == employeeId);
        employeeList.add(targetEmployee);
        return targetEmployee;
    }

    public void removeEmployee(int employeeId) {
        List<Employee> employeeList = createTestEmployees();
        employeeList.removeIf(employee -> employee.getId() == employeeId);
    }
}
