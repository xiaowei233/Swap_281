package com.swap281.controller.item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swap281.model.item.Item;
import com.swap281.model.item.ItemCategory;
import com.swap281.model.item.dto.ItemFull;
import com.swap281.repository.item.ItemCategoryRepository;
import com.swap281.repository.item.ItemRepository;

@RestController
@RequestMapping("/item/list")
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:23333", "http://localhost:8080" })
public class ItemListController {

	private ItemRepository _itemRepo;
	private ItemCategoryRepository _itemCategoryRepo;

	@Autowired
	Client _client;

	@Autowired
	public ItemListController(ItemRepository itemRepo, ItemCategoryRepository itemCategoryRepo) {
		this._itemRepo = itemRepo;
		this._itemCategoryRepo = itemCategoryRepo;
	}

	@GetMapping("/all")
	public List<ItemFull> getItemId() {
		List<ItemFull> articles = _itemRepo.getItemFull();

		return articles;
	}

	// return all categories
	@GetMapping("/dropdown")
	public List<ItemCategory> getCategories() {
		return this._itemCategoryRepo.findAll();
	}

	// return a list of items by a single category id
	@GetMapping("/category/{id}")
	public List<Item> getItemByCategory(@PathVariable Long id) {
		return this._itemRepo.findItemByCategory(id);
	}

	// filter?category=1,2,3,4,5
	@GetMapping("/filter")
	public List<ItemFull> filterByCategoryIdList(@RequestParam List<Integer> category) {
		return this._itemRepo.findItemByCategoryList(category);
	}

	// slect ..... from.... where ... order by ?1 ?2
	// order by price asc
	// search?keyword=ddd&category=7,7,7&
	@GetMapping("/search")
	public List<ItemFull> searchByKeyword(@RequestParam String keyword, @RequestParam List<Integer> category,
			@RequestParam String sort) {
		// System.out.println("sort:\t" + sort);
		System.out.println("--------------------------------------");
		List<ItemFull> articles;
		ArrayList<Long> result = new ArrayList<Long>();
		if (!keyword.equals("") && !keyword.equals("undefined")) {
			QueryBuilder matchSpecificFieldQuery = QueryBuilders.multiMatchQuery(keyword, "title", "description");
			SearchResponse Sresponse = _client.prepareSearch().setTypes().setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
					.setPostFilter(matchSpecificFieldQuery).execute().actionGet();
			SearchHits hits = Sresponse.getHits();
			SearchHit[] searchHits = hits.getHits();
			if (searchHits.length == 0)
				return null;
			for (SearchHit hit : searchHits) {
				Map<String, Object> sourceAsMap = hit.getSourceAsMap();
				System.out.println(hit.toString());
				System.out.println(sourceAsMap.get("id"));
				Long pKey = Long.valueOf((int) (sourceAsMap.get("id")));
				result.add(pKey);
			}
			articles = _itemRepo.getItemFull(result);
		} else {
			articles = _itemRepo.getItemFull();
			System.out.println("No Keyword Input:\t");
		}
		if (category.size() != 0) {
			ArrayList<Long> temp = new ArrayList<Long>();
			for (int i = 0; i < articles.size(); i++) {
				temp.add(articles.get(i).item.id);
			}
			articles = _itemRepo.filterSearchByCategoryList(temp, category);
			System.out.println("Category Input:\t");
		}
		if (!sort.equals("")) {
			String[] splited = sort.split("-");
			ArrayList<Long> temp = new ArrayList<Long>();
			for (int i = 0; i < articles.size(); i++) {
				temp.add(articles.get(i).item.id);
			}
			if (splited[0].equals("price")) {
				if (splited[1].equals("ASC"))
					articles = _itemRepo.sortByAscdPrice(temp);
				else if (splited[1].equals("DESC"))
					articles = _itemRepo.sortByDescPrice(temp);
			} else if (splited[0].equals("createDate")) {
				if (splited[1].equals("ASC"))
					articles = _itemRepo.sortByAscdDate(temp);
				else if (splited[1].equals("DESC"))
					articles = _itemRepo.sortByDescDate(temp);
			}
		}
		return articles;
	}
}
