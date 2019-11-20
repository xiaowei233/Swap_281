package com.swap281.repository.user;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.swap281.model.item.Item;
import com.swap281.model.user.UserRecentViewItem;

public interface UserRecentViewItemRepository extends JpaRepository<UserRecentViewItem, Long> {

	@Query(value = "SELECT i FROM Item i JOIN UserRecentViewItem ui ON i.id = ui.item_id WHERE ui.user_id = ?1")		
	public List<Item> getRecentViewItem(Long user_id);
	
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
