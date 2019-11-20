package com.swap281.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swap281.model.item.Item;
import com.swap281.repository.item.ItemRepository;


@RestController
@RequestMapping("/user/items")
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:23333", "http://localhost:8080" })
public class UserItemController {

	private ItemRepository _itemRepo;
	
    @Autowired
    public UserItemController(ItemRepository itemRepo) {
        this._itemRepo = itemRepo;
    }

	@GetMapping("/{id}")
	public List<Item> getPostedItems(@PathVariable Long id)
	{
		List<Item> articles = _itemRepo.GetUserItems(id);
		
		return articles;
	}
}
