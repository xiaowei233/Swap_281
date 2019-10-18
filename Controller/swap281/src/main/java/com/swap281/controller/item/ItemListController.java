package com.swap281.controller.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
	public List<Item> getItemId() {
		List<Item> articles = _itemRepo.findAll();

		return articles;
	}

	@GetMapping("/lowToHigh")
	public List<Item> lowToHigh() {
		List<Item> articles = _itemRepo.findAll(Sort.by(Sort.Direction.ASC, "price"));

		return articles;
	}

	@GetMapping("/highToLow")
	public List<Item> highToLow() {
		List<Item> articles = _itemRepo.findAll(Sort.by(Sort.Direction.DESC, "price"));

		return articles;
	}

	@GetMapping("/earlyToLate")
	public List<Item> earlyToLate() {
		List<Item> articles = _itemRepo.findAll(Sort.by(Sort.Direction.ASC, "createDate"));

		return articles;
	}

	@GetMapping("/lateToEarly")
	public List<Item> lateToEarly() {
		List<Item> articles = _itemRepo.findAll(Sort.by(Sort.Direction.DESC, "createDate"));

		return articles;
	}

	@GetMapping("/search")
	public List<Item> search() {
		Item itemFilter = new Item();
		Specification<Item> spec = new ItemSpecification(itemFilter);
		List<Item> articles = _itemRepo.findAll(spec);

		return articles;
	}

}
