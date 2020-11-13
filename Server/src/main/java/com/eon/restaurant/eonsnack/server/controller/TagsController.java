package com.eon.restaurant.eonsnack.server.controller;

import com.eon.restaurant.eonsnack.server.entity.Restaurant;
import com.eon.restaurant.eonsnack.server.entity.Tags;
import com.eon.restaurant.eonsnack.server.model.TagModel;
import com.eon.restaurant.eonsnack.server.model.assembler.TagModelAssembler;
import com.eon.restaurant.eonsnack.server.service.TagsService;
import com.sun.xml.bind.v2.runtime.unmarshaller.TagName;
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
@RequestMapping("/tags")
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

        PagedModel<TagModel> collModel = pagedResourcesAssembler.toModel(tags, tagModelAssembler);

        return new ResponseEntity<>(collModel, HttpStatus.OK);
    }

    @GetMapping("/tags/{id}")
    public ResponseEntity<TagModel> getTagsById(@PathVariable("id") int id) {

        return tagsService.findById(id)
                .map(tagModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
