package com.swap281.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.swap281.model.item.dto.ItemFull;
import com.swap281.model.user.User;

@RepositoryRestResource
public interface UserItemRepository  extends JpaRepository<User, Long>{

    @Query("SELECT new com.swap281.model.item.dto.ItemFull(i, u.username, c.condition) from Item i JOIN User u ON i.user_id = u.id JOIN ItemCondition c ON i.condition_id = c.id JOIN UserWishList ui ON i.id = ui.item_id WHERE ui.user_id = ?1 ORDER BY i.createDate DESC")
	public List<ItemFull> getFavoritedItemList(Long user_id);
	
	
}
