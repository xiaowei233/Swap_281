package com.swap281.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.swap281.model.user.UserPostedItem;

@RepositoryRestResource
public interface UserPostedItemsRepository extends JpaRepository<UserPostedItem, Long>{

}
