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
    public List<Employee> getEmployees(){
        return employeeList;
    }

    @PostMapping()
    public Employee crateEmployee(@RequestBody Employee newEmployee){
        employeeList.add(newEmployee);
    }
}
