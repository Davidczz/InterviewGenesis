package com.genesisconsulting.interviewProject.service;

import com.genesisconsulting.interviewProject.exception.EntityNotFoundException;
import com.genesisconsulting.interviewProject.model.Address;

public interface AddressService {
    Address saveAddress(Address address);
    Address updateAddress(Address address);
    void deleteAddress(Address address) throws EntityNotFoundException;
}
