package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return employeeRepository.getAllEmployees();
    }

    public List<Employee> getEmployeesByGender(String gender) {
        return employeeRepository.getEmployeesByGender(gender);
    }

    public List<Employee> getEmployeesByPage(Integer page, Integer pageSize) {
        int firstIndex = page * pageSize - 1;
        int lastIndex = (page + 1) * pageSize - 1;
        return employeeRepository.getEmployeesByIndex(firstIndex, lastIndex);
    }

    public Employee getEmployeeById(int employeeId) {
        return employeeRepository.getEmployeeById(employeeId);
    }

    public Employee addEmployee(Employee newEmployee) {
        return employeeRepository.addEmployee(newEmployee);
    }

    public Employee updateEmployInfo(int employeeId, Employee targetEmployee) {
        return employeeRepository.updateEmployInfo(employeeId, targetEmployee);
    }

    public void removeEmployee(int employeeId) {
        employeeRepository.removeEmployee(employeeId);
    }
}
