package com.swap281.controller.item;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swap281.model.item.Item;
import com.swap281.repository.item.ItemDetailRepository;


@RestController
@RequestMapping("/item/detail")
public class ItemDetailController {

	private ItemDetailRepository _itemRepo;
	

    @Autowired
    public ItemDetailController(ItemDetailRepository itemRepo) {
        this._itemRepo = itemRepo;
    }
    
	@GetMapping("/{id}")
	public Item getItemId(@PathVariable Long id)
	{
		return _itemRepo.findById(id).orElseThrow(null);
	}

}