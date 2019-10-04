package com.swap281.configurer;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;

import com.swap281.model.item.Item;
import com.swap281.repository.item.ItemDetailRepository;
import com.swap281.repository.item.ItemListRepository;

/**
 * IDs are not returned by RestRepository by default. I like them exposed so that the client can easily find
 * the ID of created and listed resources.
 * */
@Component
public class RepositoryConfigurer implements RepositoryRestConfigurer {

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    config.exposeIdsFor(ItemDetailRepository.class);
    config.exposeIdsFor(ItemListRepository.class);
    config.exposeIdsFor(Item.class);

  }
}