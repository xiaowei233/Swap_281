package com.swap281.model.item.repo;

import java.util.Optional;
import java.util.concurrent.Future;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import com.swap281.model.core.BaseRepository;
import com.swap281.model.item.table.Item;

public interface IItemDetailRepository extends BaseRepository<Item, Long>  {
    
    @Async
    @Query("SELECT i FROM Item i where i.id = :id") 
    Future<String> findItemById(@Param("id") Long id);
    
    @Query("SELECT i FROM Item i where i.title = ?1 AND t.description = ?2")
    public Optional<Item> editItemDescription(String title, String description);

}