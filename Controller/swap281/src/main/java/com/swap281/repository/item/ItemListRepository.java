package com.swap281.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.swap281.model.item.Item;

@RepositoryRestResource
public interface ItemListRepository extends JpaRepository<Item, Long>{

}