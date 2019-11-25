package com.genesisconsulting.interviewProject.repository;

import com.genesisconsulting.interviewProject.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
