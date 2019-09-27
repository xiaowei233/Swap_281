//package com.swap281.repository.item.repo;
//
//import java.util.List;
//
//import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import com.swap281.repository.BaseRepository;
//
//@Repository
//public interface ItemListRepository extends BaseRepository<Item, Long>  {
//    @Query("SELECT i.name, i.title, i.descrption FROM Item i")
//    public List<Item> getAllItems();
//
//}