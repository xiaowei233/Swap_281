package com.swap281.model.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item_detail")
public class ItemDetail {
	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "item_id", length = 50)
    public Long itemId;

    @Column(name = "detail_description")
    public String description;
    
    @Column(name = "photo")
    public byte[] photo;
    
    public ItemDetail(Long itemId, String descritption, byte[] photo)
    {
    	this.itemId = itemId;
    	this.description = descritption;
    	this.photo = photo;
    }
    
    public ItemDetail() {
    	
    }
}
