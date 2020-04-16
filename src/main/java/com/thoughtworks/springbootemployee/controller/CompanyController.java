package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("companies")
public class CompanyController {
    private List<Company> companyList = new ArrayList<Company>();

    @GetMapping()
    public List<Company> getEmployees(@RequestParam(required = false) Integer page,
                                      @RequestParam(required = false) Integer pageSize) {
        if (page != null && pageSize != null) {
            int firstIndex = page * pageSize - 1;
            int lastIndex = (page + 1) * pageSize - 1;
            return companyList.subList(firstIndex, lastIndex);
        }
        return companyList;
    }

    @GetMapping("/{companyId}")
    public Company getCompanyById(@PathVariable("companyId") int companyId) {
        return companyList
                .stream()
                .filter(company -> company.getId() == companyId)
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> getEmployeeByCompanyId(@PathVariable("companyId") int companyId) {
        Company targetCompany = companyList
                .stream()
                .filter(company -> company.getId() == companyId)
                .findFirst()
                .orElse(null);
        if (targetCompany == null) {
            return null;
        }
        return targetCompany.getEmployees();
    }

    @PostMapping()
    public Company addCompany(@RequestBody Company newCompany) {
        companyList.add(newCompany);
        return newCompany;
    }

    @PutMapping("/{companyId}")
    public Company updateEmployeeInfo(@PathVariable("companyId") int companyId, @RequestBody Company targetCompany) {
        companyList.removeIf(employee -> employee.getId() == companyId);
        companyList.add(targetCompany);
        return targetCompany;
    }

    @DeleteMapping("/{companyId}")
    public void removeEmployee(@PathVariable("companyId") int companyId) {
        companyList.removeIf(company -> company.getId() == companyId);
    }
}
