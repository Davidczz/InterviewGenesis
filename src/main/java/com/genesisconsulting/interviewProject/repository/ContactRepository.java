package com.genesisconsulting.interviewProject.repository;

import com.genesisconsulting.interviewProject.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
