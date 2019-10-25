package com.swap281.controller.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swap281.model.item.Item;
import com.swap281.repository.item.ItemRepository;


@RestController
@RequestMapping("/item/list")
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:23333", "http://localhost:8080" })
public class ItemListController {

	private ItemRepository _itemRepo;
	
    @Autowired
    public ItemListController(ItemRepository itemRepo) {
        this._itemRepo = itemRepo;
    }

	@GetMapping("/all")
	public List<Item> getItemId()
	{
		List<Item> articles = _itemRepo.findAll();
		
		return articles;
	}
}
