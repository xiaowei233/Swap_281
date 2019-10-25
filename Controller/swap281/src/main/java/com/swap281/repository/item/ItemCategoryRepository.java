package com.swap281.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.swap281.model.item.ItemCategory;

@RepositoryRestResource
public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {

}
