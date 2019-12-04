package com.swap281.repository.item;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.swap281.model.item.Item;
import com.swap281.model.item.dto.ItemFull;

@RepositoryRestResource
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT new com.swap281.model.item.dto.ItemFull(i, u.username, c.condition, COUNT(uwl.id)) from Item i JOIN User u ON i.user_id = u.id JOIN ItemCondition c ON i.condition_id = c.id LEFT JOIN UserWishList uwl ON i.id = uwl.item_id WHERE i.id = ?1 GROUP BY i.id, u.username, c.condition")
    ItemFull getItemDetailWithNumUserFavorited(Long id);

    @Query("SELECT new com.swap281.model.item.dto.ItemFull(i, u.username, c.condition) from Item i JOIN User u ON i.user_id = u.id JOIN ItemCondition c ON i.condition_id = c.id WHERE i.id = ?1")
    ItemFull getItemDetail(Long id);

    @Query("SELECT u FROM Item u WHERE u.categoryId = ?1")
    List<Item> findItemByCategory(Long id);

    // @Query("SELCT i.id FROM Item i")
    // ArrayList<Long> getAllItemId();

    @Query("SELECT new com.swap281.model.item.dto.ItemFull(i, u.username, c.condition) from Item i JOIN User u ON i.user_id = u.id JOIN ItemCondition c ON i.condition_id = c.id WHERE i.categoryId IN (?1) ORDER BY i.createDate DESC")
    List<ItemFull> findItemByCategoryList(List<Integer> idList);

    @Query("SELECT u from Item u where u.user_id = ?1")
    public List<Item> GetUserItems(Long user_id);

    @Query("SELECT new com.swap281.model.item.dto.ItemFull(i, u.username, c.condition) from Item i JOIN User u ON i.user_id = u.id JOIN ItemCondition c ON i.condition_id = c.id ORDER BY i.createDate DESC")
    public List<ItemFull> getItemFull();

    @Query("SELECT i.id from Item i")
    public List<Long> getItemFullIds();
    
    @Query("SELECT i.id from Item i WHERE i.id in (?1)")
    public List<Long> getItemFull(List<Long> idList);
    
    @Query("SELECT i.id from Item i WHERE i.id IN (?1)AND i.categoryId IN (?2)")
    public List<Long> getItemIdsByCategoryList(List<Long> itemList, List<Integer> idList);

    @Query("SELECT new com.swap281.model.item.dto.ItemFull(i, u.username, c.condition) from Item i JOIN User u ON i.user_id = u.id JOIN ItemCondition c ON i.condition_id = c.id WHERE i.id IN (?1)AND i.categoryId IN (?2) ORDER BY i.createDate DESC")
    List<ItemFull> filterSearchByCategoryList(List<Long> itemList, List<Integer> idList);

    @Query("SELECT new com.swap281.model.item.dto.ItemFull(i, u.username, c.condition) from Item i JOIN User u ON i.user_id = u.id JOIN ItemCondition c ON i.condition_id = c.id WHERE i.id in (?1) ORDER BY i.price ASC")
    List<ItemFull> sortByAscdPrice(List<Long> itemList);

    @Query("SELECT new com.swap281.model.item.dto.ItemFull(i, u.username, c.condition) from Item i JOIN User u ON i.user_id = u.id JOIN ItemCondition c ON i.condition_id = c.id WHERE i.id in (?1) ORDER BY i.price DESC")
    List<ItemFull> sortByDescPrice(List<Long> itemList);

    @Query("SELECT new com.swap281.model.item.dto.ItemFull(i, u.username, c.condition) from Item i JOIN User u ON i.user_id = u.id JOIN ItemCondition c ON i.condition_id = c.id WHERE i.id in (?1) ORDER BY i.createDate ASC")
    List<ItemFull> sortByAscdDate(List<Long> itemList);

    @Query("SELECT new com.swap281.model.item.dto.ItemFull(i, u.username, c.condition) from Item i JOIN User u ON i.user_id = u.id JOIN ItemCondition c ON i.condition_id = c.id WHERE i.id in (?1) ORDER BY i.createDate DESC")
    List<ItemFull> sortByDescDate(List<Long> itemList);

}