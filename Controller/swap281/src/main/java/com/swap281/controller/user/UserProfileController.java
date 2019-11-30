package com.swap281.controller.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swap281.model.item.dto.AvatarChange;
import com.swap281.model.user.User;
import com.swap281.model.user.UserPostedItem;
import com.swap281.repository.user.UserAccountRepository;
import com.swap281.repository.user.UserPostedItemsRepository;

@RestController
@RequestMapping("/user/profile")
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:23333", "http://localhost:8080" })
public class UserProfileController {
	@Autowired
	private UserAccountRepository _userRepo;
	
	@Autowired
	private UserPostedItemsRepository _postedItemRepo;
	
	@GetMapping("user-profile-info/{username}")
	private User getUserProfileInfo(@PathVariable("username") String username) {
		User user =  _userRepo.findByName(username);
		user.password = null;
		return user;
	}
	
	@PostMapping("update_user_avatar")
	private void updateUserAvatar(@RequestBody AvatarChange avatar) {
		_userRepo.updateAvatar(avatar.userId, avatar.avatar);
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
		usr.full_name = updatedUser.first_name + " " + updatedUser.last_name;
		User user = _userRepo.save(usr);
		user.password = null;
		return user;
			}
	
}
