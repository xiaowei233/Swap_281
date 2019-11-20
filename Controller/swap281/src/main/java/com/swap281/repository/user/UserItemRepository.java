package com.swap281.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.swap281.model.item.Item;
import com.swap281.model.user.User;

@RepositoryRestResource
public interface UserItemRepository  extends JpaRepository<User, Long>{

	@Query(value = "SELECT i FROM Item i JOIN UserWishList ui ON i.id = ui.item_id WHERE ui.user_id = ?1")		
	public List<Item> getFavoritedItemList(Long user_id);
	
	
}
