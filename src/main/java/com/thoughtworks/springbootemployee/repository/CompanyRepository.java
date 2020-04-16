package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.thoughtworks.springbootemployee.CompanyFactory.createTestCompanies;

@Repository
public class CompanyRepository {
    public List<Company> getAllCompanies() {
        return createTestCompanies();
    }

    public List<Company> getCompaniesByIndex(int firstIndex, int lastIndex) {
        return createTestCompanies().subList(firstIndex, lastIndex);
    }

    public Company getCompanyById(int companyId) {
        return createTestCompanies()
                .stream()
                .filter(company -> company.getId() == companyId)
                .findFirst()
                .orElse(null);
    }

    public List<Employee> getEmployeesByCompanyId(int companyId) {
        Company targetCompany = createTestCompanies()
                .stream()
                .filter(company -> company.getId() == companyId)
                .findFirst()
                .orElse(null);
        if (targetCompany == null) {
            return new ArrayList<>();
        }
        return targetCompany.getEmployees();
    }

    public Company addCompany(Company newCompany) {
        createTestCompanies().add(newCompany);
        return newCompany;
    }


    public Company updateEmployeeInfo(int companyId, Company targetCompany) {
        List<Company> companyList = createTestCompanies();
        companyList.removeIf(employee -> employee.getId() == companyId);
        companyList.add(targetCompany);
        return targetCompany;
    }
}
