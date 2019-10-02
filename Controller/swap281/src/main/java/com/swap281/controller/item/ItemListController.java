package com.swap281.controller.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swap281.repository.item.ItemListRepository;


@RestController
@RequestMapping("/item/list")
public class ItemListController {

	private ItemListRepository _itemRepo;
	
    @Autowired
    public ItemListController(ItemListRepository itemRepo) {
        this._itemRepo = itemRepo;
    }
    
	@GetMapping("/all")
	public String getItemId()
	{
		return "miaomiaomiao";
	}
}
