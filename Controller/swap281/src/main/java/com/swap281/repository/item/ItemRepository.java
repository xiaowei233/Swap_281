package com.swap281.repository.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.swap281.model.item.Item;
import com.swap281.model.item.dto.ItemFull;

@RepositoryRestResource
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT u FROM Item u WHERE u.categoryId = ?1")
    List<Item> findItemByCategory(Long id);

    @Query("SELECT new com.swap281.model.item.dto.ItemFull(i, u.username, c.condition) from Item i JOIN User u ON i.user_id = u.id JOIN ItemCondition c ON i.condition_id = c.id WHERE i.categoryId IN (?1) ORDER BY i.createDate DESC")
    List<ItemFull> findItemByCategoryList(List<Integer> idList);

    @Query("SELECT u from Item u where u.user_id = ?1")
    public List<Item> GetUserItems(Long user_id);

    @Query("SELECT new com.swap281.model.item.dto.ItemFull(i, u.username, c.condition) from Item i JOIN User u ON i.user_id = u.id JOIN ItemCondition c ON i.condition_id = c.id ORDER BY i.createDate DESC")
    public List<ItemFull> getItemFull();

    @Query("SELECT u FROM Item u WHERE u.id in (?1) AND u.categoryId in (?2)")
    List<Item> filterSearchByCategoryList(List<Long> itemList, List<Integer> idList);

    @Query("SELECT u FROM Item u WHERE u.id in (?1) ORDER BY price ASC")
    List<Item> sortByAscdPrice(List<Long> itemList);

    @Query("SELECT u FROM Item u WHERE u.id in (?1) ORDER BY price DESC")
    List<Item> sortByDescPrice(List<Long> itemList);

    @Query("SELECT u FROM Item u WHERE u.id in (?1) ORDER BY price ASC")
    List<Item> sortByAscdDate(List<Long> itemList);

    @Query("SELECT u FROM Item u WHERE u.id in (?1) ORDER BY price DESC")
    List<Item> sortByDescDate(List<Long> itemList);
}