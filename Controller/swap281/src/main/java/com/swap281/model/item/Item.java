package com.swap281.model.item;

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
    private Long id;

    @Column(name = "name", length = 50)
    private String title;

    public Item(String name)
    {
    	this.title = name;
    }
    
    public Item()
    {
    }
}
