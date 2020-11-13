package com.eon.restaurant.eonsnack.server.repository;

import com.eon.restaurant.eonsnack.server.entity.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "address", path = "addresses")
@CrossOrigin("http://localhost:4200")
public interface AddressRepository extends PagingAndSortingRepository<Address,Integer> {

    Address findByRestaurantId(long id);
}

