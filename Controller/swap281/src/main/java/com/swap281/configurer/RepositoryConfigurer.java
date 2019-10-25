package com.swap281.configurer;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;

import com.swap281.model.item.Item;
import com.swap281.model.item.ItemCategory;
import com.swap281.model.user.User;
import com.swap281.repository.UserAccountRepository;
import com.swap281.repository.item.ItemCategoryRepository;
import com.swap281.repository.item.ItemRepository;

/**
 * IDs are not returned by RestRepository by default. I like them exposed so that the client can easily find
 * the ID of created and listed resources.
 * */
@Component
public class RepositoryConfigurer implements RepositoryRestConfigurer {

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    config.exposeIdsFor(ItemRepository.class);
    config.exposeIdsFor(Item.class);
    config.exposeIdsFor(ItemCategory.class);    
    config.exposeIdsFor(ItemCategoryRepository.class);
    config.exposeIdsFor(UserAccountRepository.class);
    config.exposeIdsFor(User.class);
  }
}