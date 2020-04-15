package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("employee")
public class RESTController {
    private List<Employee> employeeList = new ArrayList<Employee>();

    @GetMapping()
    public List<Employee> getEmployees() {
        return employeeList;
    }

    @PostMapping()
    public Employee addEmployee(@RequestBody Employee newEmployee) {
        employeeList.add(newEmployee);
        return newEmployee;
    }

    @PutMapping()
    public Employee updateEmployeeInfo(@RequestBody Employee targetEmployee) {
        employeeList.removeIf(employee -> employee.getId() == targetEmployee.getId());
        employeeList.add(targetEmployee);
        return targetEmployee;
    }

    @DeleteMapping()
    public void removeEmployee(@RequestBody Employee targetEmployee) {
        employeeList.removeIf(employee -> employee.getId() == targetEmployee.getId());
    }
}
