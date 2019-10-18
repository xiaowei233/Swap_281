package com.swap281.controller.item;

import com.swap281.model.item.Item;
import com.swap281.model.item.ItemCategory;
import com.swap281.repository.item.ItemCategoryRepository;
import com.swap281.repository.item.ItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/item/detail")
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:23333", "http://localhost:8080" })
public class ItemDetailController {

    private ItemRepository _itemRepo;
    private ItemCategoryRepository _itemCategoryRepo;

    @Autowired
    public ItemDetailController(ItemRepository itemRepo, ItemCategoryRepository itemCategoryRepo) {
        this._itemRepo = itemRepo;
        this._itemCategoryRepo = itemCategoryRepo;
    }

    @GetMapping(value = "/category-drop-down")
    public List<ItemCategory> getCategoryFilter() {
        return _itemCategoryRepo.findAll();
    }

    @PostMapping(value = "/post")
    public Item postNewItem(@RequestBody Item newItem) {
        return _itemRepo.save(newItem);
    }
    
    @GetMapping(value = "/{id}")
    public Item getItemDetail(@PathVariable Long id)
    {
    	return _itemRepo.findById(id).get();
    }
}