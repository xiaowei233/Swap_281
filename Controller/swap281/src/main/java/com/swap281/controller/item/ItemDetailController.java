package com.swap281.controller.item;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swap281.model.item.Item;
import com.swap281.model.item.ItemCategory;
import com.swap281.repository.item.ItemCategoryRepository;
import com.swap281.repository.item.ItemDetailRepository;
import com.swap281.repository.item.ItemListRepository;


@RestController
@RequestMapping("/item/detail")
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:23333", "http://localhost:8080" })

public class ItemDetailController {

	private ItemDetailRepository _itemRepo;
	private ItemCategoryRepository _itemCategoryRepo;


    @Autowired
    public ItemDetailController(ItemDetailRepository itemRepo, ItemListRepository itemListRepo, ItemCategoryRepository itemCategoryRepo) {
        this._itemRepo = itemRepo;
        this._itemCategoryRepo = itemCategoryRepo;
    }
    
    
    @GetMapping(value = "/category-drop-down")
    public List<ItemCategory> getCategoryFilter(){
    	return _itemCategoryRepo.findAll();
    }
    
    @PostMapping(value = "/post")
    public Item postNewItem(@RequestBody Item newItem)
    {
    	return _itemRepo.save(newItem);
    }
    
}