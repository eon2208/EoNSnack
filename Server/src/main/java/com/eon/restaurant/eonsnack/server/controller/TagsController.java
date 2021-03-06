package com.eon.restaurant.eonsnack.server.controller;

import com.eon.restaurant.eonsnack.server.entity.Tags;
import com.eon.restaurant.eonsnack.server.model.TagModel;
import com.eon.restaurant.eonsnack.server.model.assembler.TagModelAssembler;
import com.eon.restaurant.eonsnack.server.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RepositoryRestController
@RequestMapping("/api")
public class TagsController {

    private final TagsService tagsService;

    private final TagModelAssembler tagModelAssembler;

    @Autowired
    private PagedResourcesAssembler<Tags> pagedResourcesAssembler;

    public TagsController(TagsService tagsService, TagModelAssembler tagModelAssembler) {
        this.tagsService = tagsService;
        this.tagModelAssembler = tagModelAssembler;
    }

    @GetMapping("/tags")
    public ResponseEntity<PagedModel<TagModel>> getAllTags(@RequestParam(value = "page", defaultValue = "0", name = "page") int page,
                                                           @RequestParam(value = "size", defaultValue = "20") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        Page<Tags> tags = tagsService.findAllTags(pageable);

        if(tags.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        PagedModel<TagModel> collModel = pagedResourcesAssembler.toModel(tags, tagModelAssembler);

        return new ResponseEntity<>(collModel, HttpStatus.OK);
    }

    @GetMapping("/tags/{id}")
    public ResponseEntity<TagModel> getTagsById(@PathVariable("id") int id) {

        return tagsService.findById(id)
                .map(tagModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("not found tag with id: " + id));
    }
}
