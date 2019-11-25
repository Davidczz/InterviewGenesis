package com.genesisconsulting.interviewProject.service;

import com.genesisconsulting.interviewProject.exception.EntityNotFoundException;
import com.genesisconsulting.interviewProject.model.Address;
import com.genesisconsulting.interviewProject.model.Company;
import com.genesisconsulting.interviewProject.repository.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ContactService contactService;

    private final static String ENTITY_NAME = "company";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Company saveCompany(Company company) {
        if(company.getAddresses() != null){
            List<Address> addresses = new ArrayList<>();
            company.getAddresses().forEach(address -> {
                addresses.add(addressService.saveAddress(address));
            });
            company.setAddresses(addresses);
        }
        return companyRepository.save(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElseGet(()->{
            log.error("Company with id "+id+ " not found");
            throw new EntityNotFoundException(id, ENTITY_NAME);
        });
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company updateCompany(Company updatedCompany) {
        if(updatedCompany.getId() == null) return null;
        return companyRepository.findById(updatedCompany.getId())
                .map(company -> {
                    if(updatedCompany.getName() != null) company.setName(updatedCompany.getName());
                    if(updatedCompany.getTvaNumber() != null) company.setTvaNumber(updatedCompany.getTvaNumber());
                    if(updatedCompany.getAddresses() != null) {
                        updatedCompany.getAddresses().forEach(c->{
                            addressService.saveAddress(c);
                        });
                        company.setAddresses(updatedCompany.getAddresses());
                    }

                    return companyRepository.save(company);
                })
                .orElseGet(() -> saveCompany(updatedCompany));
    }

    @Override
    public Company addEmployeeToCompany(Long contactId, Long companyId) {
        Company company = getCompanyById(companyId);
        company.getEmployees().add(contactService.findContactById(contactId));
        return companyRepository.save(company);
    }

    @Override
    public void deleteCompany(Long id) {
        if(!companyRepository.existsById(id)){
            log.error("Company with id "+id+ " not found");
            throw new EntityNotFoundException(id, ENTITY_NAME);
        }
        companyRepository.deleteById(id);
    }

}
