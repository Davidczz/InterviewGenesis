package com.genesisconsulting.interviewProject.service;

import com.genesisconsulting.interviewProject.exception.EntityNotFoundException;
import com.genesisconsulting.interviewProject.model.Company;

import java.util.List;

public interface CompanyService {
    Company saveCompany(Company company);
    Company getCompanyById(Long id) throws EntityNotFoundException;
    List<Company> getAllCompanies();
    Company updateCompany(Company company);
    Company addEmployeeToCompany(Long contactId, Long companyId);
    void deleteCompany(Long id) throws EntityNotFoundException;
}
