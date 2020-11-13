package com.eon.restaurant.eonsnack.server.controller;

import com.eon.restaurant.eonsnack.server.entity.Address;
import com.eon.restaurant.eonsnack.server.entity.Restaurant;
import com.eon.restaurant.eonsnack.server.model.AddressModel;
import com.eon.restaurant.eonsnack.server.model.RestaurantModel;
import com.eon.restaurant.eonsnack.server.model.assembler.AddressModelAssembler;
import com.eon.restaurant.eonsnack.server.service.AddressService;
import com.eon.restaurant.eonsnack.server.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RepositoryRestController
@RequestMapping("/api")
public class AddressController {

    private final AddressService addressService;
    private final AddressModelAssembler addressModelAssembler;

    public AddressController(AddressService addressService, AddressModelAssembler addressModelAssembler, PagedResourcesAssembler<Address> pagedResourcesAssembler) {
        this.addressService = addressService;
        this.addressModelAssembler = addressModelAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    private final PagedResourcesAssembler<Address> pagedResourcesAssembler;

    @GetMapping("/addresses")
    public ResponseEntity<CollectionModel<AddressModel>> getAllAddresses(@RequestParam(value = "page", defaultValue = "0", name = "page") int page,
                                                                         @RequestParam(value = "size", defaultValue = "20") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        Page<Address> addressPage = addressService.findAll(pageable);

        PagedModel<AddressModel> collModel = pagedResourcesAssembler.toModel(addressPage, addressModelAssembler);
        return new ResponseEntity<>(collModel, HttpStatus.OK);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<AddressModel> getAddressById(@PathVariable("id") int id) {

        return addressService.findById(id)
                .map(addressModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
