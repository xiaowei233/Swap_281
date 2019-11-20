package com.swap281.model.item.dto;

import com.swap281.model.item.Item;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemFull {

    public Item item;
    
    public String full_name;
    
    
//    public ItemFull(String title, Date createDate, int categoryId, String description, BigDecimal price, long user_id,boolean availability, long trade_method_id, byte[] thumbnail, String full_name)
//    {
//    	this.title = title;
//    	this.createDate = createDate;
//    	this.categoryId = categoryId;
//    	this.description = description;
//    	this.price = price;
//    	this.user_id = user_id;
//    	this.availability = availability;
//    	this.trade_method_id = trade_method_id;
//    	this.thumbnail = thumbnail;
//    	this.full_name = full_name;
//    }
    
    public ItemFull(Item item, String fullName) {
    	this.item = item;
    	this.full_name = fullName;
    }
}