package com.eon.restaurant.eonsnack.server.exception;

import lombok.Getter;

@Getter
public class RestaurantNotFoundException extends RuntimeException {
    private final Long id;

    public RestaurantNotFoundException(final long id) {
        super("Restaurant could not be find with id:" + id);
        this.id = id;
    }

}
