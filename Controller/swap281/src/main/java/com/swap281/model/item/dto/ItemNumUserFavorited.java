package com.swap281.model.item.dto;

import com.swap281.model.item.Item;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemNumUserFavorited {
	public Item item;
	public Long num;
	
    public ItemNumUserFavorited(Item item, Long num) {
    	this.item = item;
    	this.num = num;
    }
}
