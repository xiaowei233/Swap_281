//package com.swap281.configurer;
//
//
//import javax.persistence.EntityManager;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.swap281.service.HibernateSearchServiceImpl;
//
//@EnableAutoConfiguration
//@Configuration
//public class HibernateSearchConfiguration {
//
//    @Autowired
//    private EntityManager bentityManager;
//
//    @Bean
//    HibernateSearchServiceImpl hibernateSearchService() {
//        HibernateSearchServiceImpl hibernateSearchServiceImpl = new HibernateSearchServiceImpl(bentityManager);
//        hibernateSearchServiceImpl.initializeHibernateSearch();
//        return hibernateSearchServiceImpl;
//    }
//}
