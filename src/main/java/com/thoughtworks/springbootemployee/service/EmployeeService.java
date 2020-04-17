package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> getEmployeesByGender(String gender) {
        return employeeRepository.findAllByGender(gender);
    }

    public List<Employee> getEmployeesByPage(Integer page, Integer pageSize) {
        return employeeRepository.findAll(PageRequest.of(page, pageSize)).getContent();
    }

    public Employee getEmployeeById(int employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    public Employee addEmployee(Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    public Employee updateEmployeeInfo(int employeeId, Employee targetEmployee) {
        Employee oriEmployee = employeeRepository.findById(employeeId).orElse(null);
        if(oriEmployee==null){
            return null;
        }
        oriEmployee.updateEmployee(targetEmployee);
        return employeeRepository.save(oriEmployee);
    }

    public void removeEmployee(int employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
