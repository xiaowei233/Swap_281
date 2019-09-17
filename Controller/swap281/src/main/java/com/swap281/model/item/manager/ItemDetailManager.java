package com.swap281.model.item.manager;

import com.swap281.model.StructuredResponse;
import com.swap281.model.item.repo.IItemDetailRepository;
import com.swap281.model.item.table.Item;

public class ItemDetailManager implements IItemDetailManager {
	final IItemDetailRepository itemDetailRepository;

	public ItemDetailManager (itemDetailRepository _itemDetailRepository) {
		itemDetailRepository = _itemDetailRepository;
	}

	@Override
	public Item findItem(int id) {
		// TODO Auto-generated method stub
		return itemDetailRepository.getClass();
	}

	@Override
	public Item deleteItem(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StructuredResponse postItem(Item item) {
		// TODO Auto-generated method stub
		return null;
	}
	
}