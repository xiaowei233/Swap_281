package com.swap281.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.swap281.model.item.dto.ItemNumUserFavorited;
import com.swap281.model.user.User;
import com.swap281.model.user.UserWishList;

@RepositoryRestResource
public interface UserWishListRepository  extends JpaRepository<UserWishList, Long>{

	@Query("SELECT w FROM UserWishList w WHERE w.user_id = ?1 and w.item_id = ?2")
	public UserWishList findFavoritedItemRecord(Long userId, Long itemId);
	
	@Query("SELECT new com.swap281.model.item.dto.ItemNumUserFavorited(i, COUNT(u.id)) FROM Item i LEFT JOIN UserWishList u ON i.id = u.item_id WHERE i.user_id = ?1 GROUP BY i.id")
	public List<ItemNumUserFavorited> numberOfPeopleFavorited(Long id);
	
	@Query("SELECT new com.swap281.model.user.User(u.id, u.username) FROM UserWishList uwl JOIN User u on uwl.user_id = u.id WHERE uwl.item_id = ?1")
    List<User> getAllFavUser(Long id);
}
