package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import io.restassured.http.ContentType;
import io.restassured.mapper.TypeRef;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest {

    @Autowired
    EmployeeService mockEmployeeService;
    List<Employee> dummyEmployees;

    @Before
    public void setUp() throws Exception {
        mockEmployeeService= Mockito.mock(EmployeeService.class);
        RestAssuredMockMvc.standaloneSetup(new EmployeeController(mockEmployeeService));

        Employee employeeA = new Employee(1, "Tom", 18, "Male", 20000, 1);
        Employee employeeB = new Employee(1, "Tim", 18, "Male", 20000, 1);
        Employee employeeC = new Employee(1, "Tommy", 18, "Male", 20000, 1);
        dummyEmployees = new ArrayList<>();
        dummyEmployees.add(employeeA);
        dummyEmployees.add(employeeB);
        dummyEmployees.add(employeeC);
    }

    @Test
    public void should_get_all_employees(){
        doReturn(dummyEmployees).when(mockEmployeeService).getAllEmployees();
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .when()
                .get("/employees");

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        List<Employee> employees = response.getBody().as(new TypeRef<List<Employee>>(){
            @Override
            public Type getType(){
                return super.getType();
            }
        });
        Assert.assertEquals(3, employees.size());
    }

    @Test
    public void should_get_employees_by_gender(){
        doReturn(dummyEmployees).when(mockEmployeeService).getEmployeesByGender(any());
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .params("gender", "Male")
                .when()
                .get("/employees");

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        List<Employee> employees = response.getBody().as(new TypeRef<List<Employee>>(){
            @Override
            public Type getType(){
                return super.getType();
            }
        });
        Assert.assertEquals(3, employees.size());
    }

    @Test
    public void should_get_employee_by_id(){
        doReturn(dummyEmployees.get(0)).when(mockEmployeeService).getEmployeeById(any());
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .when()
                .get("/employees/1");
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        Employee employee = response.getBody().as(Employee.class);
        Assert.assertEquals(1, employee.getId().longValue());
    }

    @Test
    public void should_add_employee(){
        doReturn(dummyEmployees.get(0)).when(mockEmployeeService).addEmployee(any());
        Employee employee = new Employee(5, "Haha", 13, "Male", 100000, 1);
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .body(employee)
                .when()
                .post("/employees");

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void should_update_employee(){
        doReturn(dummyEmployees.get(2)).when(mockEmployeeService).updateEmployeeInfo(any(), any());
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .body(dummyEmployees.get(2))
                .when()
                .put("/employees/1");

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        Employee employee = response.getBody().as(Employee.class);
        Assert.assertEquals("Tommy", employee.getName());
    }

    @Test
    public void should_delete_employee(){
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .when()
                .delete("/employees/1");

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }
}
