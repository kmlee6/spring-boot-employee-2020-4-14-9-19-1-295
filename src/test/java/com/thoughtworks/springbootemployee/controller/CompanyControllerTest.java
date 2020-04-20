package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
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

import static com.thoughtworks.springbootemployee.EmployeeFactory.createTestEmployees;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyControllerTest {
    @Autowired
    private CompanyController companyController;
    private CompanyService mockCompanyService;
    List<Company> dummyCompanies;

    @Before
    public void setUp() throws Exception {
        mockCompanyService= Mockito.mock(CompanyService.class);
        RestAssuredMockMvc.standaloneSetup(new CompanyController(mockCompanyService));
        dummyCompanies = new ArrayList<>();
        Company google = new Company(1, "Google", new ArrayList<>());
        List<Employee> dummyEmployees = new ArrayList<>();
        dummyEmployees.add(new Employee(1, "Tom", 18, "Male", 20000, 1));
        dummyEmployees.add(new Employee(1, "Tim", 18, "Male", 20000, 1));
        dummyEmployees.add(new Employee(1, "Tommy", 18, "Male", 20000, 1));
        google.setEmployees(dummyEmployees);
        dummyCompanies.add(google);
        dummyCompanies.add(new Company(2, "Facebook", new ArrayList<>()));
        dummyCompanies.add(new Company(3, "Amazon", new ArrayList<>()));
    }

    @Test
    public void should_get_all_companies() {
        doReturn(dummyCompanies).when(mockCompanyService).getAllCompanies();
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .when()
                .get("/companies");
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        List<Company> company = response.getBody().as(new TypeRef<List<Company>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        Assert.assertEquals(3, company.size());
    }

    @Test
    public void should_get_1_company_when_page_equal_1_and_page_size_equal_to_1() {
        doReturn(dummyCompanies.subList(1, 2)).when(mockCompanyService).getCompaniesByPage(any(), any());
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .when()
                .get("/companies?page=2&pageSize=1");
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        List<Company> companies = response.getBody().as(new TypeRef<List<Company>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        Assert.assertEquals(1, companies.size());
        Assert.assertEquals(2, companies.get(0).getId().longValue());
    }

    @Test
    public void should_get_google() {
        doReturn(dummyCompanies.get(0)).when(mockCompanyService).getCompanyById(any());
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .when()
                .get("/companies/2");
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        Company company = response.getBody().as(Company.class);
        Assert.assertEquals("Google", company.getCompanyName());
    }

    @Test
    public void should_get_google_employees() {
        doReturn(dummyCompanies.get(0).getEmployees()).when(mockCompanyService).getEmployeesByCompanyId(any());
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .when()
                .get("/companies/2/employees");
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        List<Employee> employee = response.getBody().as(new TypeRef<List<Employee>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        Assert.assertEquals(3, employee.size());
    }

    @Test
    public void should_be_able_to_add_new_company() {
        Company company = new Company(5, "ThoughWork", createTestEmployees());
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .body(company)
                .when()
                .post("/companies");

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void should_be_able_to_update_company() {
        Company company = new Company(2, "ThoughWork", createTestEmployees());
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .body(company)
                .when()
                .put("/companies/2");

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void should_be_able_to_delete_company() {
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .when()
                .delete("/companies/1");

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }
}
