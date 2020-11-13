package com.eon.restaurant.eonsnack.server.repository;


import com.eon.restaurant.eonsnack.server.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "tag", path = "tags")
@CrossOrigin("http://localhost:4200")
public interface TagsRepository extends PagingAndSortingRepository<Tags, Integer> {

    Tags findByName(String name);

    Boolean existsByName(String name);
}
