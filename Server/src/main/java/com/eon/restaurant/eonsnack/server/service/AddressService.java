package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    Page<Address> findAll(Pageable pageable);

    Optional<Address> findById(int id);

    Address findByRestaurantId(long id);

}
