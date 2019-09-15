package com.swap281.controller;
import com.swap281.model.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import com.google.gson.Gson;


@RestController
public class ItemEditController {
    @RequestMapping(value="item", method = RequestMethod.POST)
    public StructuredResponse postNewItem(
        @RequestParam("id") long id, 
        @RequestParam("title") String title,
        @RequestParam("description") String description,
        @RequestParam("category") int category,
        @RequestParam("date") int date,
        @RequestParam("method") int method,
        @RequestParam("price") float price,
        @RequestParam("available") boolean available,
        @RequestParam("availableTime") String availableTime,
        @RequestParam("wanted") String wanted) {
        int res = database.insertNewItem(id, title, description, category, date, method, price, available,
        availableTime, wanted);    
        if (res == -1)
            return new StructuredResponse("error", "Failed to insert item", null);
        return new StructuredResponse("ok", null, res);
    }

    @RequestMapping(value="item", method = RequestMethod.DELETE)
    public StructuredResponse deleteItemById(
        @RequestParam("id") long id) {
        int res = database.deleteItem(id);
        if (res == -1)
                return new StructuredResponse("error", "Delete item: " + id + "failed", null);
            return new StructuredResponse("ok", null, res);
    }

    @RequestMapping(value="item", method = RequestMethod.PUT)
    public StructuredResponse editItemById(
        @RequestParam("id") long id) {
            //TODO
        return null;
    }

    
}