package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

public class CompanyServiceTest {
//    @Mock
//    private EmployeeRepository employeeRepository;
    private EmployeeRepository mockEmployeeRepository= Mockito.mock(EmployeeRepository.class);
    private EmployeeService employeeService;

    @Before
    public void setUp() throws Exception {
//        RestAssuredMockMvc.standaloneSetup(new EmployeeService(employeeRepository));
        employeeService = new EmployeeService(mockEmployeeRepository);
    }

    @Test
    public void should_get_all_employee(){
        Mockito.when(mockEmployeeRepository.findById(1)).thenReturn(Optional.of(new Employee(1, "Tommy", 18, "Male", 200, 1)));
        Employee employee = new Employee(1, "Tommy", 18, "Male", 200, 1);
        employeeService.addEmployee(employee);
        Mockito.verify(mockEmployeeRepository, Mockito.times(1)).save(employee);
    }
}
