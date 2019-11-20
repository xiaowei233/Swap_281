package com.swap281.controller.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.Fuzziness;
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

	// return all categories
	@GetMapping("/dropdown")
	public List<ItemCategory> getCategories()
	{
		return this._itemCategoryRepo.findAll();
	}

	// return a list of items by a single category id
	@GetMapping("/category/{id}")
	public List<Item> getItemByCategory(@PathVariable Long id) {
		return this._itemRepo.findItemByCategory(id);
	}

	// filter?category=1,2,3,4,5
	@GetMapping("/filter")
	public List<Item> filterByCategoryIdList(@RequestParam List<Integer> category) {
		return this._itemRepo.findItemByCategoryList(category);
	}

	@GetMapping("/search/{keyword}")
	public List<Item> searchByKeyword(@PathVariable String keyword)
	{
		QueryBuilder matchSpecificFieldQuery= QueryBuilders
				.multiMatchQuery(
					    keyword, 
					    "title", "description"       
					);
		SearchResponse Sresponse = _client.prepareSearch()
				  .setTypes()
				  .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				  .setPostFilter(matchSpecificFieldQuery)
				  .execute()
				  .actionGet();
        SearchHits hits = Sresponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        ArrayList<Long> result = new ArrayList();
        for (SearchHit hit : searchHits) {
            System.out.println("entered title search hits");
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            Long pKey = Long.valueOf((int)(sourceAsMap.get("id")));

            result.add(pKey);
        }
   
		return _itemRepo.findAllById(result);	
		}
	
	@GetMapping("/test/{keyword}")
	public String test(@PathVariable String keyword)
	{
		QueryBuilder matchSpecificFieldQuery= QueryBuilders
				.multiMatchQuery(
					    keyword, 
					    "title", "description"       
					);
		SearchResponse Sresponse = _client.prepareSearch()
				  .setTypes()
				  .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				  .setPostFilter(matchSpecificFieldQuery)
				  .execute()
				  .actionGet();
        SearchHits hits = Sresponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        ArrayList<Long> result = new ArrayList();
//        for (SearchHit hit : searchHits) {
//            System.out.println("entered title search hits");
//            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
//            long pKey = (long) sourceAsMap.get("id");
//
//            result.add(pKey);
//        }
        for (int i = 0; i < searchHits.length; i ++)
        	System.out.println(searchHits[i].toString());
		return searchHits[0].toString();	
		}
}
