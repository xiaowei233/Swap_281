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
	
	@Query("SELECT u FROM Item u WHERE u.categoryId in (?1)")
	List<Item> findItemByCategoryList(List<Integer> idList);

    @Query("SELECT u from Item u where u.user_id = ?1")
    public List<Item> GetUserItems(Long user_id);
    
    @Query("SELECT new com.swap281.model.item.dto.ItemFull(i, u.full_name, c.condition) from Item i JOIN User u ON i.user_id = u.id JOIN ItemCondition c ON i.condition_id = c.id")
    public List<ItemFull> getItemFull();
}