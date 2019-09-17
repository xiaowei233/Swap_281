package com.swap281.model.item.manager;

import java.util.ArrayList;

import com.swap281.model.StructuredResponse;
import com.swap281.model.item.dto.ItemData;
import com.swap281.model.item.dto.ItemSearchParameter;

public interface IItemListManager {

	StructuredResponse getItemList();

	ArrayList<ItemData> selectAllItemsFromCategory(ArrayList<Integer> categories);

	Object search(ItemSearchParameter search);

}
