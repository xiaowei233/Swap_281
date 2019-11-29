package com.swap281.model.item.dto;

import com.swap281.model.item.Item;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemFull {

    public Item item;
    
    public String username;
        
    public String condition;
    
    public Long num = (long) 0;
    
    public ItemFull(Item item, String userName, String condition) {
    	this.item = item;
    	this.username = userName;
    	this.condition = condition;
    }
    
    public ItemFull(Item item, String userName, String condition, Long num) {
    	this.item = item;
    	this.username = userName;
    	this.condition = condition;
    	this.num = num;
    }
}