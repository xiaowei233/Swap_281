package com.swap281.controller.item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swap281.model.item.Item;
import com.swap281.model.item.ItemCategory;
import com.swap281.model.item.ItemCondition;
import com.swap281.model.item.dto.ItemFull;
import com.swap281.model.user.User;
import com.swap281.model.user.UserWishList;
import com.swap281.repository.item.ItemCategoryRepository;
import com.swap281.repository.item.ItemConditionRepository;
import com.swap281.repository.item.ItemRepository;
import com.swap281.repository.user.UserWishListRepository;

@RestController
@RequestMapping("/item/detail")
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:23333", "http://localhost:8080" })
public class ItemDetailController {

    private ItemRepository _itemRepo;
    private ItemCategoryRepository _itemCategoryRepo;
    private ItemConditionRepository _itemConditionRepo;
    @Autowired
    private UserWishListRepository _userWishListRepo;

    @Autowired
    public ItemDetailController(ItemRepository itemRepo, ItemCategoryRepository itemCategoryRepo,
            ItemConditionRepository itemConditionRepo) {
        this._itemRepo = itemRepo;
        this._itemCategoryRepo = itemCategoryRepo;
        this._itemConditionRepo = itemConditionRepo;
    }

    @GetMapping(value = "/category-drop-down")
    public List<ItemCategory> getCategoryFilter() {
        return _itemCategoryRepo.findAll();
    }

    @Autowired
    Client _client;

    @GetMapping(value = "/condition-drop-down")
    public List<ItemCondition> getConditionFilter() {
        return _itemConditionRepo.findAll();
    }

    @PostMapping(value = "/post")
    public Item postNewItem(@RequestBody Item newItem) {
        Item saved_item = _itemRepo.save(newItem);
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder().startObject().field("id", saved_item.id)
                    .field("title", saved_item.title).field("description", saved_item.description).endObject();
            IndexResponse response = _client.prepareIndex("item", "list").setSource(builder).get();

        } catch (IOException e) {
            System.out.println("Error from Inserting new item to Elasticsearch: " + e.getMessage());
            e.printStackTrace();
        }
        return saved_item;
    }

    @GetMapping("/get-all-favorited-user/{id}")
    public List<User> getAllFavoritedUser(@PathVariable Long id){
    	return _userWishListRepo.getAllFavUser(id);
    }
    
    @GetMapping(value = "/{id}")
    public ItemFull getItemDetail(@PathVariable Long id) {
        return _itemRepo.getItemDetailWithNumUserFavorited(id);
    }

    @GetMapping(value = "new-favorite")
    public boolean favoriteOneItem(@RequestParam("userId") Long userId, @RequestParam("itemId") Long itemId) {
        UserWishList uwl = _userWishListRepo.findFavoritedItemRecord(userId, itemId);
        if (uwl == null) {
            _userWishListRepo.save(new UserWishList(userId, itemId));
            return true;
        } else {
            _userWishListRepo.deleteById(uwl.id);
            return false;
        }
    }

    @GetMapping(value = "check-favorite")
    public boolean checkFavorited(@RequestParam("userId") Long userId, @RequestParam("itemId") Long itemId) {
        UserWishList uwl = _userWishListRepo.findFavoritedItemRecord(userId, itemId);
        if (uwl == null)
            return false;
        return true;
    }

    @GetMapping("/similar_item/{itemId}")
    public List<Item> getSimilarItems(@PathVariable Long itemId) {
        return null;
    }

    @GetMapping(value = "/posttest")
    public List<String> postTestItem() {

        try {
            XContentBuilder builder = XContentFactory.jsonBuilder().startObject().field("id", 1).field("title", "asdf")
                    .field("description", "shitdescription").endObject();
            IndexResponse response = _client.prepareIndex("item", "list").setSource(builder).get();
            QueryBuilder matchSpecificFieldQuery = QueryBuilders.matchQuery("title", "asdf");
            SearchResponse Sresponse = _client.prepareSearch().setTypes().setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                    .setPostFilter(matchSpecificFieldQuery).execute().actionGet();
            SearchHits hits = Sresponse.getHits();
            SearchHit[] searchHits = hits.getHits();
            ArrayList<String> result = new ArrayList();
            for (SearchHit hit : searchHits) {
                System.out.println("entered title search hits");
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                String pKey = (String) sourceAsMap.get("title");
                result.add(pKey);
            }
            return result;
        } catch (IOException e) {
            System.out.println("Error from Inserting new item to Elasticsearch: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}