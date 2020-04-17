package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
        return companyRepository.findAll();
    }

    public List<Company> getCompaniesByPage(Integer page, Integer pageSize) {
        return companyRepository.findAll(PageRequest.of(page, pageSize)).getContent();
    }

    public Company getCompanyById(int companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    public List<Employee> getEmployeesByCompanyId(int companyId) {
        Company company = companyRepository.findById(companyId).orElse(null);
        if(company == null){
            return null;
        }
        return company.getEmployees();
    }

    public Company addCompany(Company newCompany) {
        return companyRepository.save(newCompany);
    }

    public Company updateEmployeeInfo(int companyId, Company targetCompany) {
        Company oriCompany = companyRepository.findById(companyId).orElse(null);
        if(oriCompany==null){
            return null;
        }
        oriCompany.updateCompany(targetCompany);
        return companyRepository.save(oriCompany);
    }

    public void removeEmployee(int companyId) {
        companyRepository.deleteById(companyId);
    }
}
