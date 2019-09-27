package com.swap281.repository.item.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.swap281.model.item.Item;

@RepositoryRestResource
public interface ItemDetailRepository extends JpaRepository<Item, Long>  {
//    
//    @Async
//    @Query("SELECT i FROM Item i where i.id = :id") 
//    Future<String> findItemById(@Param("id") Long id);
}