package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.EmployeeRequest;
import com.thoughtworks.springbootemployee.model.EmployeeResponse;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public List<Employee> getEmployees(@RequestParam(required = false) String gender,
                                       @RequestParam(required = false) Integer page,
                                       @RequestParam(required = false) Integer pageSize) {
        if (gender != null) {
            return employeeService.getEmployeesByGender(gender);
        }
        if (page != null && pageSize != null) {
            return employeeService.getEmployeesByPage(page, pageSize);
        }
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable("employeeId") int employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping()
    public Employee addEmployee(@RequestBody Employee newEmployee) {
        return employeeService.addEmployee(newEmployee);
    }

    @PutMapping("/{employeeId}")
    public EmployeeResponse updateEmployeeInfo(@PathVariable("employeeId") int employeeId, @RequestBody EmployeeRequest targetEmployee) {
        return employeeService.updateEmployeeInfo(employeeId, targetEmployee);
    }

    @DeleteMapping("/{employeeId}")
    public void removeEmployee(@PathVariable("employeeId") Integer employeeId) {
        employeeService.removeEmployee(employeeId);
    }
}
