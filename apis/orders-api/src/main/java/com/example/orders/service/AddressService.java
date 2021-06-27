package com.example.orders.service;

import com.example.orders.domain.Address;
import com.example.orders.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public void saveAddress(Address address){
        addressRepository.save(address);
    }
}
