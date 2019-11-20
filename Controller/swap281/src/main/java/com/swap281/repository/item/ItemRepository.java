package com.swap281.repository.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.swap281.model.item.Item;


@RepositoryRestResource
public interface ItemRepository extends JpaRepository<Item, Long> {

	@Query("SELECT u FROM Item u WHERE u.categoryId = ?1")
	List<Item> findItemByCategory(Long id);
	
	@Query("SELECT u FROM Item u WHERE u.categoryId in (?1)")
	List<Item> findItemByCategoryList(List<Integer> idList);
}