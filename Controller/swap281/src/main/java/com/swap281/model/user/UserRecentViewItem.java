package com.swap281.model.user;

 
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_recent_view_item")
public class UserRecentViewItem {
	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

	@Column(name = "item_id")
    public Long item_id;

	@Column(name = "user_id")
    public Long user_id;

	@Column(name = "create_date")
	public Date create_date = new Date();
	
    public UserRecentViewItem(Long id, Long user_id, Long item_id, Date create_date)
    {
    	this.id = id;
    	this.item_id = item_id;
    	this.user_id = user_id;
    	this.create_date = create_date;
    }
    
    public UserRecentViewItem(Long user_id, Long item_id)
    {
    	this.item_id = item_id;
    	this.user_id = user_id;
    }
    
    
    public UserRecentViewItem() {
    	
    }
}
