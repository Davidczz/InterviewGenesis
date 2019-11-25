package com.genesisconsulting.interviewProject.service;

import com.genesisconsulting.interviewProject.exception.EntityNotFoundException;
import com.genesisconsulting.interviewProject.model.Address;
import com.genesisconsulting.interviewProject.model.Contact;
import com.genesisconsulting.interviewProject.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    CompanyService companyService;

    @Autowired
    AddressService addressService;

    @Autowired
    ContactRepository contactRepository;

    private final static String ENTITY_NAME = "contact";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Contact saveContact(Contact contact) {
        Address address = addressService.saveAddress(contact.getAddress());
        contact.setAddress(address);
        return contactRepository.save(contact);
    }

    @Override
    public Contact findContactById(Long id) {
        return contactRepository.findById(id).orElseGet(()->{
            log.error("Contact with id "+id+ " not found");
            throw new EntityNotFoundException(id, ENTITY_NAME);
        });
    }

    @Override
    public Contact updateContact(Contact updatedContact) {
        return contactRepository.findById(updatedContact.getId())
                .map(contact -> {
                    contact.setFirstName(updatedContact.getFirstName());
                    contact.setLastName(updatedContact.getLastName());
                    contact.setAddress(addressService.saveAddress(updatedContact.getAddress()));
                    return contactRepository.save(contact);
                })
                .orElseGet(() -> saveContact(updatedContact));
    }

    @Override
    public void delete(Long id) {
        if(!contactRepository.existsById(id)){
            log.error("Contact with id "+id+ " not found");
            throw new EntityNotFoundException(id, ENTITY_NAME);
        }
        contactRepository.deleteById(id);
    }
}
