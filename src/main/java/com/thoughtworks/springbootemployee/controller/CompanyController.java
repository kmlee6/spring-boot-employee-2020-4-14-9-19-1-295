package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("companies")
public class CompanyController {
    private List<Company> companyList = new ArrayList<Company>();

    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping()
    public List<Company> getCompanies(@RequestParam(required = false) Integer page,
                                      @RequestParam(required = false) Integer pageSize) {
        if (page != null && pageSize != null) {
            return companyService.getCompaniesByPage(page, pageSize);
        }
        return companyService.getAllCompanies();
    }

    @GetMapping("/{companyId}")
    public Company getCompanyById(@PathVariable("companyId") int companyId) {
        return companyService.getCompanyById(companyId);
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> getEmployeeByCompanyId(@PathVariable("companyId") int companyId) {
        return companyService.getEmployeesByCompanyId(companyId);
    }

    @PostMapping()
    public Company addCompany(@RequestBody Company newCompany) {
        return companyService.addCompany(newCompany);
    }

    @PutMapping("/{companyId}")
    public Company updateEmployeeInfo(@PathVariable("companyId") int companyId, @RequestBody Company targetCompany) {
        return companyService.updateEmployeeInfo(companyId, targetCompany);
    }

    @DeleteMapping("/{companyId}")
    public void removeEmployee(@PathVariable("companyId") int companyId) {
        companyList.removeIf(company -> company.getId() == companyId);
    }
}
