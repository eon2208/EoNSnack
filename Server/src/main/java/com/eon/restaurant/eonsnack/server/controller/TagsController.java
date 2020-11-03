package com.eon.restaurant.eonsnack.server.controller;

import com.eon.restaurant.eonsnack.server.model.assembler.TagModelAssembler;
import com.eon.restaurant.eonsnack.server.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RepositoryRestController
@RequestMapping("/tags")
public class TagsController {

    private final TagsService tagsService;

    private final TagModelAssembler tagModelAssembler;

    public TagsController(TagsService tagsService, TagModelAssembler tagModelAssembler) {
        this.tagsService = tagsService;
        this.tagModelAssembler = tagModelAssembler;
    }
}
