package com.genesisconsulting.interviewProject.service;

import com.genesisconsulting.interviewProject.exception.EntityNotFoundException;
import com.genesisconsulting.interviewProject.model.Contact;

public interface ContactService {

    Contact saveContact(Contact contact);
    Contact findContactById(Long id) throws EntityNotFoundException;
    Contact updateContact(Contact updatedContact);
    void delete(Long id) throws EntityNotFoundException;
}
