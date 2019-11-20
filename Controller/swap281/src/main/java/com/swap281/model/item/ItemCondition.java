package com.swap281.model.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="condition")
public class ItemCondition {

	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
	
	@Column(name = "condition")
	public String condition;
	
	public ItemCondition() {
		
	}
	public ItemCondition(String condition, Long id) {
		this.id = id;
		this.condition  = condition;
	}
}
