package com.cse281.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cse281.model.Item;

@Service
public class ItemService {

	private static List<Item> items = new ArrayList<>();
	private static int idCounter = 0;

	static {
		items.add(new Item(++idCounter, "item 1", "this is item 1", 1.1));
		items.add(new Item(++idCounter, "item 2", "this is item 2", 1.2));
		items.add(new Item(++idCounter, "item 3", "this is item 3", 1.3));
		items.add(new Item(++idCounter, "item 4", "this is item 1", 1.4));
		
	}

	public List<Item> findAll() {
		return items;
	}

	public Item save(Item item) {
		if (item.getId() == -1 || item.getId() == 0) {
			item.setId(++idCounter);
			items.add(item);
		} else {
			deleteById(item.getId());
			items.add(item);
		}
		return item;
	}

	public Item deleteById(int id) {
		Item item = findById(id);

		if (item == null)
			return null;

		if (items.remove(item)) {
			return item;
		}

		return null;
	}

	public Item findById(int id) {
		for (Item item : items) {
			if (item.getId() == id) {
				return item;
			}
		}

		return null;
	}
}