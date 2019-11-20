package com.swap281.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swap281.model.item.Item;
import com.swap281.model.user.UserRecentViewItem;
import com.swap281.repository.item.ItemRepository;
import com.swap281.repository.user.UserItemRepository;
import com.swap281.repository.user.UserRecentViewItemRepository;

@RestController
@RequestMapping("/user/items")
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:23333", "http://localhost:8080" })
public class UserItemController {

	private ItemRepository _itemRepo;
	@Autowired
	private UserItemRepository _userItemRepo;
	@Autowired
	private UserRecentViewItemRepository _userRecentViewItemRepo;

	@Autowired
	public UserItemController(ItemRepository itemRepo) {
		this._itemRepo = itemRepo;
	}

	@GetMapping("/{id}")
	public List<Item> getPostedItems(@PathVariable Long id) {
		List<Item> articles = _itemRepo.GetUserItems(id);

		return articles;

	}

	@GetMapping("fav/{id}")
	public List<Item> getFavoritedItemList(@PathVariable Long id) {
		return _userItemRepo.getFavoritedItemList(id);
	}

	@GetMapping("recently_viewed/{id}")
	public List<Item> getRecentlyViewedItem(@PathVariable Long id) {
		return _userRecentViewItemRepo.getRecentViewItem(id);
	}

	@GetMapping("add_recently_viewed/{user_id}/{item_id}")
	public void addRecentlyViewedItem(@PathVariable Long user_id, @PathVariable Long item_id) {
		if (_userRecentViewItemRepo.checkIfExist(user_id, item_id) != null)
			_userRecentViewItemRepo.update(user_id, item_id);
		else
			_userRecentViewItemRepo.save(new UserRecentViewItem(user_id, item_id));
	}
}
