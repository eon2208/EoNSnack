package com.eon.restaurant.eonsnack.server.model.assembler;

import com.eon.restaurant.eonsnack.server.controller.AddressController;
import com.eon.restaurant.eonsnack.server.controller.RestaurantController;
import com.eon.restaurant.eonsnack.server.entity.Address;
import com.eon.restaurant.eonsnack.server.model.AddressModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class AddressModelAssembler extends RepresentationModelAssemblerSupport<Address, AddressModel> {

    public AddressModelAssembler() {
        super(AddressController.class, AddressModel.class);
    }

    @Override
    public AddressModel toModel(Address entity) {

        AddressModel addressModel = instantiateModel(entity);

        addressModel.add(linkTo(
                methodOn(AddressController.class)
                        .getAddressById(entity.getId()))
                .withSelfRel());
        addressModel.add(linkTo(
                methodOn(RestaurantController.class)
                    .getRestaurantByAddressId(entity.getId()))
                .withRel("restaurant"));

        addressModel.setId(entity.getId());
        addressModel.setCity(entity.getCity());
        addressModel.setFormatted(entity.getFormatted());
        addressModel.setPostal_code(entity.getPostal_code());
        addressModel.setStreet(entity.getStreet());
        addressModel.setState(entity.getState());

        return addressModel;
    }

    @Override
    public CollectionModel<AddressModel> toCollectionModel(Iterable<? extends Address> entities) {

        return StreamSupport
                .stream(entities.spliterator(), false)
                .map(this::toModel)
                .collect(Collectors.collectingAndThen(Collectors.toList(), CollectionModel:: of));
    }
}
