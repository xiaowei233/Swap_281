package com.swap281.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swap281.model.item.Item;
import com.swap281.model.item.dto.ItemFull;
import com.swap281.model.item.dto.ItemNumUserFavorited;
import com.swap281.model.user.UserRecentViewItem;
import com.swap281.repository.item.ItemRepository;
import com.swap281.repository.user.UserItemRepository;
import com.swap281.repository.user.UserRecentViewItemRepository;
import com.swap281.repository.user.UserWishListRepository;

@RestController
@RequestMapping("/user/item")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:8081", "http://localhost:23333" })
public class UserItemController {

	private ItemRepository _itemRepo;
	@Autowired
	private UserItemRepository _userItemRepo;
	@Autowired
	private UserRecentViewItemRepository _userRecentViewItemRepo;
	@Autowired
	private UserWishListRepository _userWishlistRepo;
	
	@Autowired
	public UserItemController(ItemRepository itemRepo) {
		this._itemRepo = itemRepo;
	}

	@GetMapping("/{id}")
	public List<ItemNumUserFavorited> getPostedItems(@PathVariable Long id) {
		return _userWishlistRepo.numberOfPeopleFavorited(id);
	}
	
	@GetMapping("/posted_title/{id}")
	public List<Item> getPostedItemTitle(@PathVariable Long id) {
		List<Item> articles = _itemRepo.GetUserItems(id);
		for (Item i : articles)
			i.thumbnail = null;
		return articles;

	}

	@GetMapping("fav/{id}")
	public List<ItemFull> getFavoritedItemList(@PathVariable Long id) {
		return _userItemRepo.getFavoritedItemList(id);
	}

	@GetMapping("recently_viewed/{id}")
	public List<ItemFull> getRecentlyViewedItem(@PathVariable Long id) {
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
