package com.swap281.controller.item;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.swap281.model.StructuredResponse;
import com.swap281.model.item.dto.ItemSearchParameter;
import com.swap281.model.item.manager.IItemListManager;
import com.swap281.model.item.manager.ItemListManager;

@RestController
public class ItemListController {

	final IItemListManager itemListManager;

	public ItemListController (ItemListManager _itemListManager) {
		itemListManager = _itemListManager;
	}
	
    @RequestMapping(value="item/all", method = RequestMethod.GET)
    public StructuredResponse getItemList(){
        return itemListManager.getItemList();
    }

    @RequestMapping(value="item/search", method = RequestMethod.POST)
    public StructuredResponse searchItemList(@RequestBody ItemSearchParameter search){
        return new StructuredResponse("ok", null, itemListManager.search(search));
    }

    @RequestMapping(value="item", params = {"search"}, method = RequestMethod.GET)
    public StructuredResponse getItemByKeyword(){
        //TODO
        return null;
    }
}
