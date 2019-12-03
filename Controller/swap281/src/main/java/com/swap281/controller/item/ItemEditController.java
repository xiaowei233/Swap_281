package com.swap281.controller.item;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/item/edit")
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:23333", "http://localhost:8080" })
public class ItemEditController {

    private ItemRepository _itemRepo;

    @Autowired
    public ItemEditController(ItemRepository itemRepo, ItemCategoryRepository itemCategoryRepo,
            ItemConditionRepository itemConditionRepo) {
        this._itemRepo = itemRepo;
    }

    @PostMapping(value = "/save/{id}")
    public Item saveEdit(@RequestBody Item newItem,@PathVariable Long id) {
    	newItem.id = id;
    	Item saved_item = _itemRepo.save(newItem);

//        try {
//            XContentBuilder builder = XContentFactory.jsonBuilder().startObject().field("id", saved_item.id)
//                    .field("title", saved_item.title).field("description", saved_item.description).endObject();
//            IndexResponse response = _client.prepareIndex("item", "list").setSource(builder).get();
//
//        } catch (IOException e) {
//            System.out.println("Error from Inserting new item to Elasticsearch: " + e.getMessage());
//            e.printStackTrace();
//        }
        return saved_item;
    }
    @DeleteMapping(value = "/delete/{id}")
    public void deleteItem(@PathVariable Long id) {
    	_itemRepo.deleteById(id);
    }
    
    @GetMapping(value = "/{id}")
    public ItemFull getItemDetail(@PathVariable Long id) {
        return _itemRepo.getItemDetailWithNumUserFavorited(id);
    }
}
