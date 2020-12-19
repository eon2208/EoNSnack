package com.eon.restaurant.eonsnack.server.repository;


import com.eon.restaurant.eonsnack.server.entity.Tags;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Set;

@RepositoryRestResource(collectionResourceRel = "tag", path = "tags")
@CrossOrigin("http://localhost:4200")
@Repository
public interface TagsRepository extends PagingAndSortingRepository<Tags, Integer> {

    Tags findByName(String name);

    Boolean existsByName(String name);

    @Query("select tags from Tags tags where tags.id in (:tagsId)")
    List<Tags> findAllByIdList(Set<Integer> tagsId);
}
