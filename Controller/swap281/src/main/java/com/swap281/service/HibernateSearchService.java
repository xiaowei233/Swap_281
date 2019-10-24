package com.swap281.service;

import java.util.List;

import javax.transaction.Transactional;

import com.swap281.model.item.Item;

public interface HibernateSearchService {
	@Transactional
    List<Item> magicSearch(String searchTerm);
}
