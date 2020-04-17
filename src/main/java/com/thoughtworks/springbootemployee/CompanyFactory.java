package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.model.Company;

import java.util.ArrayList;
import java.util.List;

import static com.thoughtworks.springbootemployee.EmployeeFactory.createTestEmployees;

public class CompanyFactory {
    public static List<Company> createTestCompanies() {
        List<Company> companies = new ArrayList<>();
        companies.add(new Company(1, "OOCL", createTestEmployees()));
        companies.add(new Company(2, "Google", createTestEmployees()));
        return companies;
    }
}
