package com.swap281.model.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class ItemCategory {

	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
	
	@Column(name = "category")
	public String category;
	
	public ItemCategory() {
		
	}
	public ItemCategory(String category, Long id) {
		this.id = id;
		this.category  = category;
	}
}
