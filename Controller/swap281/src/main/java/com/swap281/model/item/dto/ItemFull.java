package com.swap281.model.item.dto;

import com.swap281.model.item.Item;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemFull {

    public Item item;
    
    public String full_name;
    
    public String condition;
    
    public ItemFull(Item item, String fullName, String condition) {
    	this.item = item;
    	this.full_name = fullName;
    	this.condition = condition;
    }
}