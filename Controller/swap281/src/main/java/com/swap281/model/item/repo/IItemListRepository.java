package com.swap281.model.item.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.swap281.model.core.BaseRepository;
import com.swap281.model.item.table.Item;

@Repository
public interface IItemListRepository extends BaseRepository<Item, Long>  {
    @Query("SELECT i.name, i.title, i.descrption FROM Item i")
    public List<Item> getAllItems();

    // SQL result is returned to the caller, in our case the manager class 
    @Query(
        "SELECT *"+
        "FROM Item i"+
        "WHERE i.category = :category"+    
        "ORDER BY"+
        "    CASE WHEN :sortDir = 0 THEN i.price END ASC,"+
        "    CASE WHEN :sortDir = 1 THEN i.price END DESC")
    public List<Item> searchItemList(String category, int sortDir);

}