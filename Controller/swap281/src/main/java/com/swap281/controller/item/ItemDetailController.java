package com.swap281.controller.item;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swap281.model.item.ItemDetail;
import com.swap281.repository.item.ItemDetailRepository;


@RestController
@RequestMapping("/item/detail")
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:23333", "http://localhost:8080" })
public class ItemDetailController {

	private ItemDetailRepository _itemRepo;
	

    @Autowired
    public ItemDetailController(ItemDetailRepository itemRepo) {
        this._itemRepo = itemRepo;
    }
    
	@GetMapping("/{id}")
	public ItemDetail getItemId(@PathVariable Long id)
	{
		return _itemRepo.findById(id).orElseThrow(null);
	}

}