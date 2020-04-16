package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.thoughtworks.springbootemployee.EmployeeFactory.createTestEmployees;

@RestController
@RequestMapping("employees")
public class EmployeeController {
    private List<Employee> employeeList = new ArrayList<Employee>();

    public EmployeeController(){
        employeeList = createTestEmployees();
    }

    @GetMapping()
    public List<Employee> getEmployees(@RequestParam(required = false) String gender,
                                       @RequestParam(required = false) Integer page,
                                       @RequestParam(required = false) Integer pageSize) {
        if (gender != null) {
            return employeeList
                    .stream()
                    .filter(employee -> employee.getGender().equals(gender))
                    .collect(Collectors.toList());
        }
        if (page != null && pageSize != null) {
            int firstIndex = page * pageSize - 1;
            int lastIndex = (page + 1) * pageSize - 1;
            return employeeList.subList(firstIndex, lastIndex);
        }
        return employeeList;
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable("employeeId") int employeeId) {
        return employeeList
                .stream()
                .filter(employee -> employee.getId() == employeeId)
                .findFirst()
                .orElse(null);
    }

    @PostMapping()
    public Employee addEmployee(@RequestBody Employee newEmployee) {
        employeeList.add(newEmployee);
        return newEmployee;
    }

    @PutMapping("/{employeeId}")
    public Employee updateEmployeeInfo(@PathVariable("employeeId") int employeeId, @RequestBody Employee targetEmployee) {
        employeeList.removeIf(employee -> employee.getId() == employeeId);
        employeeList.add(targetEmployee);
        return targetEmployee;
    }

    @DeleteMapping("/{employeeId}")
    public void removeEmployee(@PathVariable("employeeId") int employeeId) {
        employeeList.removeIf(employee -> employee.getId() == employeeId);
    }
}
