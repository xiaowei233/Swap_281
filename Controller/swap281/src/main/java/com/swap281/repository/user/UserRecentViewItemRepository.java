package com.swap281.repository.user;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.swap281.model.item.dto.ItemFull;
import com.swap281.model.user.UserRecentViewItem;

public interface UserRecentViewItemRepository extends JpaRepository<UserRecentViewItem, Long> {

	@Query(value = "SELECT new com.swap281.model.item.dto.ItemFull(i, u.username, c.condition) from Item i JOIN User u ON i.user_id = u.id JOIN ItemCondition c ON i.condition_id = c.id JOIN UserRecentViewItem ui ON i.id = ui.item_id WHERE ui.user_id = ?1 ORDER BY i.createDate DESC")
	public List<ItemFull> getRecentViewItem(Long user_id);
	
	@Query(value = "UPDATE UserRecentViewItem ui SET ui.create_date = CURRENT_DATE WHERE ui.user_id = ?1 AND ui.item_id = ?2; " + 
			"   if @@rowcount = 0" + 
			"   begin" + 
			"      insert into table (user_id, item_id) values (?1, ?2)" + 
			"   end", nativeQuery = true)		
	public UserRecentViewItem saveOrUpdate(Long user_id, Long item_id);
	
	@Query(value = "SELECT ui FROM UserRecentViewItem ui WHERE ui.user_id = ?1 AND ui.item_id = ?2")
	public UserRecentViewItem checkIfExist(Long user_id, Long item_id);

	@Modifying
	@Transactional
	@Query(value = "UPDATE UserRecentViewItem ui SET ui.create_date = CURRENT_DATE WHERE ui.user_id = ?1 AND ui.item_id = ?2")
	public void update(Long user_id, Long item_id);
}
