package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.entity.Address;
import com.eon.restaurant.eonsnack.server.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Page<Address> findAll(Pageable pageable) {
        return addressRepository.findAll(Pageable.unpaged());
    }

    @Override
    public Optional<Address> findById(int id) {
        return addressRepository.findById(id);
    }

    @Override
    public Address findByRestaurantId(long id) {
        return addressRepository.findByRestaurantId(id);
    }
}
