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
    
    public ItemFull(Item item, String userName, String condition) {
    	this.item = item;
    	this.username = userName;
    	this.condition = condition;
    }
}