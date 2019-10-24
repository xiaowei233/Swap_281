package com.swap281.controller.item;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swap281.model.item.Item;
import com.swap281.service.HibernateSearchService;


@RestController
@RequestMapping("/item/search")
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:23333", "http://localhost:8080" })

public class itemSearchController {


	@Autowired
	private HibernateSearchService hibernateSearchServiceImpl;
	@GetMapping("/{searchTerm}")
	public List<Item> search(@PathVariable String searchTerm)
	{
		List<Item> itemList = hibernateSearchServiceImpl.magicSearch(searchTerm);
		return itemList;
	}
}
