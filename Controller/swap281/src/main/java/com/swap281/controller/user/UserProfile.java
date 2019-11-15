package com.swap281.controller.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swap281.model.user.User;
import com.swap281.model.user.UserPostedItem;
import com.swap281.repository.UserAccountRepository;
import com.swap281.repository.UserPostedItemsRepository;

@RestController
@RequestMapping("/user/profile")
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:23333", "http://localhost:8080" })

public class UserProfile {
	@Autowired
	private UserAccountRepository _userRepo;
	
	@Autowired
	private UserPostedItemsRepository _postedItemRepo;
	
	@GetMapping("user-profile-info/{userId}")
	private Optional<User> getUserProfileInfo(@PathVariable("userId") Long userId) {
		return _userRepo.findById(userId);
	}
	@GetMapping("user-posted-items/{userId}")
	private Optional<UserPostedItem> getUserPostedItems(@PathVariable("userId") Long userId) {
		return _postedItemRepo.findById(userId);
//		List<Long> idList = _postedItemRepo.findById(userId);
//		findAllById(idList)
	}
	@PostMapping("edit")
	private User editUserProfile(@RequestBody  User updatedUser) {
		Optional<User> u = _userRepo.findById(updatedUser.id);
		User usr = u.get();
		usr.username = updatedUser.username;
		usr.first_name = updatedUser.first_name;
		usr.last_name = updatedUser.last_name;
		return _userRepo.save(usr);
		
		//return usr;
	}
	
}
