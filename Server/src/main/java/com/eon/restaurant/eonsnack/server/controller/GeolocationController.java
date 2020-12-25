package com.eon.restaurant.eonsnack.server.controller;

import com.eon.restaurant.eonsnack.server.model.GeolocationModel;
import com.eon.restaurant.eonsnack.server.model.assembler.GeolocationModelAssembler;
import com.eon.restaurant.eonsnack.server.service.GeolocationService;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RepositoryRestController
@RequestMapping("/api")
public class GeolocationController {

    final
    GeolocationModelAssembler geolocationModelAssembler;

    final
    GeolocationService geolocationService;

    public GeolocationController(GeolocationModelAssembler geolocationModelAssembler, GeolocationService geolocationService) {
        this.geolocationModelAssembler = geolocationModelAssembler;
        this.geolocationService = geolocationService;
    }

    @GetMapping("/geolocation/{id}")
    public ResponseEntity<GeolocationModel> getGeolocationById(@PathVariable("id") int id) {

        return geolocationService.findById(id)
                .map(geolocationModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
