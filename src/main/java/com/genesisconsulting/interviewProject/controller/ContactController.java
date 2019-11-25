package com.genesisconsulting.interviewProject.controller;

import com.genesisconsulting.interviewProject.model.Contact;
import com.genesisconsulting.interviewProject.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;


    @PostMapping("/contact")
    public Contact addContact(@RequestBody Contact contact){
       return contactService.saveContact(contact);
    }

    @GetMapping("/contact/{id}")
    private Contact getContactById(@PathVariable Long id){
        return contactService.findContactById(id);
    }

    @PutMapping("/contact/{id}")
    private Contact updateContact(@RequestBody Contact updatedContact, @PathVariable Long id){
        if(updatedContact.getId() == null) updatedContact.setId(id);
        return contactService.updateContact(updatedContact);
    }

    @DeleteMapping("/contact/{id}")
    private void deleteById(@PathVariable Long id){
        contactService.delete(id);
    }

}
