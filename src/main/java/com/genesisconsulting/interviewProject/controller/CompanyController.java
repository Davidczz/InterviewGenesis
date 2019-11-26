package com.genesisconsulting.interviewProject.controller;

import com.genesisconsulting.interviewProject.model.Company;
import com.genesisconsulting.interviewProject.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping("/company")
    public Company addCompany(@RequestBody Company company){
       return companyService.saveCompany(company);
    }

    @GetMapping("/company/{id}")
    private Company getById(@PathVariable Long id){
        return companyService.getCompanyById(id);
    }

    @PutMapping("/company/{id}")
    private Company update(@RequestBody Company updatedCompany, @PathVariable Long id){
        if(updatedCompany.getId() == null) updatedCompany.setId(id);
        return companyService.updateCompany(updatedCompany);
    }

    @PutMapping("/company/{id}/addEmployee/{contactId}")
    private Company addEmployee(@PathVariable Long contactId, @PathVariable Long id){
        return companyService.addEmployeeToCompany(contactId, id);
    }

    @DeleteMapping("/company/{id}/removeEmployee/{contactId}")
    private Company removeEmployee(@PathVariable Long contactId, @PathVariable Long id){
        return companyService.removeEmployee(id, contactId);
    }

    @DeleteMapping("/company/{id}")
    private void deleteById(@PathVariable Long id){
        companyService.deleteCompany(id);
    }

}
