package com.swap281.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.swap281.Swap281Application;
import com.swap281.model.*;
import com.swap281.repository.Database;

import java.util.ArrayList;

@RestController
public class ViewController {
    Database database = Swap281Application.getDatabase();
    @RequestMapping(value="item/all", method = RequestMethod.GET)
    public StructuredResponse getItemList(){
        ArrayList<ItemData> selectAll = database.selectAllItems();
        if (selectAll == null)
                return new StructuredResponse("error", "Get all item returns null", null);
            return new StructuredResponse("ok", null, selectAll);
    }

    @RequestMapping(value="item", params = {"id"}, method = RequestMethod.GET)
    public StructuredResponse getItemById(
        @RequestParam("id") int id){
        ItemData select = database.selectOneItem(id);
        if (select == null) {
            String errorMessage = "GET by ID: The specified id" + id + "does not exist in the database";
            return new StructuredResponse("error", errorMessage, null);
        }
        return new StructuredResponse("ok", null, select);
    }

    @RequestMapping(value="item", params = {"category"}, method = RequestMethod.GET)
    public StructuredResponse getItemByCategory(
        @RequestParam("category") String category){
        ArrayList<Integer> categories = new ArrayList<Integer>(); // Create an ArrayList object
        String unParsedCategory[] = getStringArrayFromText(category);
        for (int j = 0; j < unParsedCategory.length; j++) {
            int cat = convertCategoryToInt(unParsedCategory[j]);
            if (cat == -1)
                return new StructuredResponse("error", "category not defined",null);
            categories.add(cat);
            }
        ArrayList<ItemData> result = database.selectAllItemsFromCategory(categories);
        return new StructuredResponse("ok", null, result);
    }

    @RequestMapping(value="item", params = {"search"}, method = RequestMethod.GET)
    public StructuredResponse getItemByKeyword(){
        //TODO
        return null;
    }

    @RequestMapping(value="item",params = {"pricerank"}, method = RequestMethod.GET)
    public StructuredResponse sortItemByPrice(
        @RequestParam("pricerank") String pricerank){
        ArrayList<ItemData> result = database.selectAllItems();
        if (pricerank.equals("asc"))
            result = sortByAsc(result);
        else if (pricerank.equals("dsc"))
            result = sortByDsc(result);
        else
            return new StructuredResponse("error", "price rank not defined", null);
        return new StructuredResponse("ok", null, result);
    }

    @RequestMapping(value="item",params = {"pricerank", "category"}, method = RequestMethod.GET)
    public StructuredResponse getItemByCategorySorted(
        @RequestParam("pricerank") String pricerank,
        @RequestParam("category") String category){
        ArrayList<Integer> categories = new ArrayList<Integer>(); // Create an ArrayList object
        String unParsedCategory[] = getStringArrayFromText(category);
        for (int j = 0; j < unParsedCategory.length; j++) {
            int cat = convertCategoryToInt(unParsedCategory[j]);
            if (cat == -1)
                return new StructuredResponse("error", "category not defined",null);
            categories.add(cat);
            }
        ArrayList<ItemData> result = database.selectAllItemsFromCategory(categories);
        if (pricerank.equals("asc"))
            result = sortByAsc(result);
        else if (pricerank.equals("dsc"))
            result = sortByDsc(result);
        else
            return new StructuredResponse("error", "price rank not defined", null);
        return new StructuredResponse("ok", null, result);
    }

    @RequestMapping(value="item",params = {"search", "pricerank"}, method = RequestMethod.GET)
    public StructuredResponse getItemKeywordSorted(){
        //TODO
        return null;
    }

    /**
     * Split the input into string array using delimiter "-"
     * 
     * @param text Unparsed string with different elements connected by a single "-"
     * @return String array that contains each element
     */
    private static String[] getStringArrayFromText(String text) {
        String[] list = text.split("-");
        return list;
    }

    /**
     * Helper method to convert an category string into the corresponding int in the
     * database
     * 
     * @param text String that contains the category
     * @return the int that respresents the input category in the database
     */
    private static int convertCategoryToInt(String text) {
        if (text.equals("car"))
            return 1;
        else if (text.equals("school"))
            return 2;
        else if (text.equals("electronics"))
            return 3;
        else if (text.equals("furniture"))
            return 4;
        else
            return -1;
    }

    /**
     * Very inefficient insertion sort helper method. However, since we will only
     * deal with small dataset in this stage we will be fine. Future sorting will be
     * handled by Elasticsearch
     * 
     * @param list the ItemData list we want to sort in descending order
     * @return the ItemData in descending order
     */
    private static ArrayList<ItemData> sortByDsc(ArrayList<ItemData> list) {
        int i, j;
        ItemData key;
        for (i = 1; i < list.size(); i++) {
            key = list.get(i);
            j = i - 1;
            /*
             * Move elements of arr[0..i-1], that are greater than key, to one position
             * ahead of their current position
             */
            while (j >= 0 && list.get(j).itemPrice < key.itemPrice) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1, key);
        }
        return list;
    }

    /**
     * Very inefficient insertion sort helper method. However, since we will only
     * deal with small dataset in this stage we will be fine. Future sorting will be
     * handled by Elasticsearch
     * 
     * @param list the ItemData list we want to sort in ascending order
     * @return the ItemData in descending order
     */
    private static ArrayList<ItemData> sortByAsc(ArrayList<ItemData> list) {
        int i, j;
        ItemData key;
        for (i = 1; i < list.size(); i++) {
            key = list.get(i);
            j = i - 1;
            /*
             * Move elements of arr[0..i-1], that are greater than key, to one position
             * ahead of their current position
             */
            while (j >= 0 && list.get(j).itemPrice > key.itemPrice) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1, key);
        }
        return list;
    }
}
