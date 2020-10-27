package com.eon.restaurant.eonsnack.server.model.assembler;

import com.eon.restaurant.eonsnack.server.controller.HalLinksController.AddressController;
import com.eon.restaurant.eonsnack.server.controller.HalLinksController.RestaurantController;
import com.eon.restaurant.eonsnack.server.controller.WebController;
import com.eon.restaurant.eonsnack.server.entity.Address;
import com.eon.restaurant.eonsnack.server.model.AddressModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class AddressModelAssembler extends RepresentationModelAssemblerSupport<Address, AddressModel> {

    public AddressModelAssembler() {
        super(WebController.class, AddressModel.class);
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

        CollectionModel<AddressModel> addressModels = super.toCollectionModel(entities);

        addressModels.add(linkTo(methodOn(AddressController.class).getAllAddresses()).withSelfRel());

        return addressModels;
    }
}
