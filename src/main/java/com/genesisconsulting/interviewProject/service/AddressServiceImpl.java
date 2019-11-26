package com.genesisconsulting.interviewProject.service;

import com.genesisconsulting.interviewProject.exception.EntityNotFoundException;
import com.genesisconsulting.interviewProject.model.Address;
import com.genesisconsulting.interviewProject.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    private final static String ENTITY_NAME = "address";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(Address address) {
        if(address.getId() == null) return null;
        return saveAddress(address);
    }

    @Override
    public void deleteAddress(Address address) {
        if(!addressRepository.existsById(address.getId())){
            log.error("Address with id "+address.getId()+ " not found");
            throw new EntityNotFoundException(address.getId(), ENTITY_NAME);
        }
        addressRepository.delete(address);
    }
}
