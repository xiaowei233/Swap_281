package com.swap281.model.item;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item {
	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "title", length = 50)
    public String title;
    
    @Column(name = "create_date")
    public Date createDate;
    
    @Column(name = "category_id")
    public int  categoryId;
    
    @Column(name = "item_detail_id") 
    public long itemDetailId;
    
    @Column(name = "description")
    public String description;
    
    @Column(name = "price")
    public BigDecimal price;
    
    public Item(String title, Date createDate, int categoryId, long itemDetailId, String descritption, BigDecimal price)
    {
    	this.title = title;
    	this.createDate = createDate;
    	this.categoryId = categoryId;
    	this.itemDetailId = itemDetailId;
    	this.description = descritption;
    	this.price = price;
    }
    
    public Item() {
    	
    }
}
