package com.swap281.controller.item;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swap281.model.StructuredResponse;
import com.swap281.model.item.manager.IItemDetailManager;
import com.swap281.model.item.manager.ItemDetailManager;
import com.swap281.model.item.table.Item;

@RestController
public class ItemDetailController {
	final IItemDetailManager itemDetailManager;

	public ItemDetailController (ItemDetailManager _itemManager) {
		itemDetailManager = _itemManager;
	}
	
    @RequestMapping(value="item", params = {"id"}, method = RequestMethod.GET)
    public @ResponseBody Item getItemById(@RequestParam("id") int id){
        return itemDetailManager.findItem(id);
    }

    @RequestMapping(value="item", method = RequestMethod.POST)
    public StructuredResponse postNewItem(@RequestBody Item item) {
    	if (item == null) return null;
    	return itemDetailManager.postItem(item);
    }

    @RequestMapping(value="item", method = RequestMethod.DELETE)
    public Item deleteItemById(@RequestParam("id") long id) {
        return itemDetailManager.deleteItem(id);
    }

    @RequestMapping(value="item/edit/{id}", method = RequestMethod.PUT)
    public Item editItemById(@PathVariable long id) {
    	if (id < 0) return null;
            //TODO
        return null;
    }
}