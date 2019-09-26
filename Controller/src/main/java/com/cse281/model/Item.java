package com.cse281.model;

public class Item {
	private int id;
	private String title;
	private String description;
	private double price;

	public Item() {

	}

	public Item(int id, String title, String description, double price) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	// @Override
	// public int hashCode() {
	// 	final int prime = 31;
	// 	int result = 1;
	// 	result = prime * result + ((description == null) ? 0 : description.hashCode());
	// 	result = prime * result + ((id == null) ? 0 : id.hashCode());
	// 	result = prime * result + ((username == null) ? 0 : username.hashCode());
	// 	return result;
	// }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		
			
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		
		return true;
	}

}