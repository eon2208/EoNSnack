package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    List<Address> findAll();

    Optional<Address> findById(int id);

    Address findByRestaurantId(long id);

}
