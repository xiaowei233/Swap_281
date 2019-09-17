package com.swap281.model.item.manager;

import com.swap281.model.StructuredResponse;
import com.swap281.model.item.table.Item;

public interface IItemDetailManager {

	Item findItem(int id);

	Item deleteItem(long id);

	StructuredResponse postItem(Item item);

	
}