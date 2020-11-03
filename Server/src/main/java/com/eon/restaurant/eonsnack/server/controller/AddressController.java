package com.eon.restaurant.eonsnack.server.controller;

import com.eon.restaurant.eonsnack.server.entity.Address;
import com.eon.restaurant.eonsnack.server.entity.Restaurant;
import com.eon.restaurant.eonsnack.server.model.AddressModel;
import com.eon.restaurant.eonsnack.server.model.RestaurantModel;
import com.eon.restaurant.eonsnack.server.model.assembler.AddressModelAssembler;
import com.eon.restaurant.eonsnack.server.service.AddressService;
import com.eon.restaurant.eonsnack.server.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RepositoryRestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;
    private final AddressModelAssembler addressModelAssembler;
    private final RestaurantService restaurantService;

    public AddressController(AddressService addressService, AddressModelAssembler addressModelAssembler, RestaurantService restaurantService) {
        this.addressService = addressService;
        this.addressModelAssembler = addressModelAssembler;
        this.restaurantService = restaurantService;
    }

    @GetMapping("/")
    public ResponseEntity<CollectionModel<AddressModel>> getAllAddresses() {
        List<Address> addresses = addressService.findAll();
        return new ResponseEntity<>(addressModelAssembler.toCollectionModel(addresses), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressModel> getAddressById(@PathVariable("id") int id) {

        return addressService.findById(id)
                .map(addressModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
