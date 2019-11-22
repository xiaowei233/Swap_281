package com.swap281.repository.user;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.swap281.model.user.User;

@RepositoryRestResource
public interface UserAccountRepository  extends JpaRepository<User, Long>{
	
	  @Query("SELECT 'found' from User u where u.username = ?1")
	  public String CheckUsername(String username);
	  
	  @Query("SELECT u from User u where u.username = ?1 and u.password = ?2 and u.is_verified = true")
	  public User Auth(String username, String password);
	  
	  @Modifying
	  @Transactional
	  @Query("UPDATE User u SET u.is_verified = true where u.token = ?1")
	  public void VerifyEmail(@Param("token") UUID token);
	  
	  @Query("SELECT u FROM User u WHERE u.username = ?1")
	  public User findByName(String username);
}
