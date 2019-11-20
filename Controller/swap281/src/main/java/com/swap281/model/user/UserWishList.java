package com.swap281.model.user;

 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_wishlist")
public class UserWishList {
	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

	@Column(name = "item_id")
    public Long item_id;

	@Column(name = "user_id")
    public Long user_id;

    public UserWishList(Long id, Long item_id, Long user_id)
    {
    	this.id = id;
    	this.item_id = item_id;
    	this.user_id = user_id;
    }
    
    public UserWishList(Long user_id, Long item_id)
    {
    	this.item_id = item_id;
    	this.user_id = user_id;
    }
    
    
    public UserWishList() {
    	
    }
}
