package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.controller.EmployeeController;
import com.thoughtworks.springbootemployee.model.Employee;
import io.restassured.http.ContentType;
import io.restassured.mapper.TypeRef;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Type;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest {

    @Autowired
    private EmployeeController employeeController;

    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.standaloneSetup(employeeController);
    }

    @Test
    public void should_get_all_employees(){
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
        Assert.assertEquals(2, employees.size());
    }

    @Test
    public void should_get_employee_by_id(){
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .when()
                .get("/employees/1");
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        Employee employee = response.getBody().as(Employee.class);
        Assert.assertEquals(1, employee.getId());
    }

    @Test
    public void should_add_employee(){
        Employee employee = new Employee(5, "Haha", 13, "Male", 100000);
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .body(employee)
                .when()
                .post("/employees");

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void should_update_employee(){
        Employee updatedEmployee = new Employee(1, "Tommy", 22, "Male", 50000);
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .body(updatedEmployee)
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
