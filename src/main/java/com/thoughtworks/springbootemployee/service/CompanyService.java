package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.getAllCompanies();
    }

    public List<Company> getCompaniesByPage(Integer page, Integer pageSize) {
        int firstIndex = page * pageSize - 1;
        int lastIndex = (page + 1) * pageSize - 1;
        return companyRepository.getCompaniesByIndex(firstIndex, lastIndex);
    }

    public Company getCompanyById(int companyId) {
        return companyRepository.getCompanyById(companyId);
    }

    public List<Employee> getEmployeesByCompanyId(int companyId) {
        return companyRepository.getEmployeesByCompanyId(companyId);
    }

    public Company addCompany(Company newCompany) {
        return companyRepository.addCompany(newCompany);
    }

    public Company updateEmployeeInfo(int companyId, Company targetCompany) {
        return companyRepository.updateEmployeeInfo(companyId, targetCompany);
    }

    public void removeEmployee(int companyId) {
        companyRepository.removeEmployee(companyId);
    }
}
