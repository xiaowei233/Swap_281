package com.swap281.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.swap281.model.user.UserWishList;

@RepositoryRestResource
public interface UserWishListRepository  extends JpaRepository<UserWishList, Long>{

	@Query("SELECT w FROM UserWishList w WHERE w.user_id = ?1 and w.item_id = ?2")
	public UserWishList findFavoritedItemRecord(Long userId, Long itemId);
}
