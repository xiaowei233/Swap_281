package com.swap281.model.item;

import java.math.BigDecimal;
import java.sql.Date;
import org.hibernate.search.annotations.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Indexed;

@Entity
@Indexed
@Table(name = "item")
public class Item {
	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "title", length = 50)
    @Field(termVector = TermVector.YES, index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    public String title;
    
    @Column(name = "create_date")
    public Date createDate;
    
    @Column(name = "category_id")
    public int  categoryId;
       
    @Column(name = "description")
    public String description;
    
    @Column(name = "price")
    public BigDecimal price;
    
    @Column(name = "user_id")
    public long user_id;
    
    @Column(name = "availability")
    public boolean availability;
    
    @Column(name = "trade_method_id")
    public long trade_method_id;
    
    @Column(name = "thumbnail")
    public byte[] thumbnail;
    public Item(String title, Date createDate, int categoryId, String descritption, BigDecimal price, long user_id,boolean availability, long trade_method_id, byte[] thumbnail)
    {
    	this.title = title;
    	this.createDate = createDate;
    	this.categoryId = categoryId;
    	this.description = descritption;
    	this.price = price;
    	this.user_id = user_id;
    	this.availability = availability;
    	this.trade_method_id = trade_method_id;
    	this.thumbnail = thumbnail;
    			
    }
    
    public Item() {
    	
    }
}
