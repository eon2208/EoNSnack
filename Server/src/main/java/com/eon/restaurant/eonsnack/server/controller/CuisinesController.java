package com.eon.restaurant.eonsnack.server.controller;

import com.eon.restaurant.eonsnack.server.entity.Cuisines;
import com.eon.restaurant.eonsnack.server.model.CuisineModel;
import com.eon.restaurant.eonsnack.server.model.assembler.CuisinesModelAssembler;
import com.eon.restaurant.eonsnack.server.service.CuisinesService;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RepositoryRestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/cuisines")
public class CuisinesController {

    private final CuisinesService cuisinesService;

    private final CuisinesModelAssembler cuisinesModelAssembler;

    private final PagedResourcesAssembler<Cuisines> pagedResourcesAssembler;

    public CuisinesController(CuisinesService cuisinesService, CuisinesModelAssembler cuisinesModelAssembler, PagedResourcesAssembler<Cuisines> pagedResourcesAssembler) {
        this.cuisinesService = cuisinesService;
        this.cuisinesModelAssembler = cuisinesModelAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuisineModel> getCuisinesById(@PathVariable("id") int id) {

        return cuisinesService.findById(id)
                .map(cuisinesModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

