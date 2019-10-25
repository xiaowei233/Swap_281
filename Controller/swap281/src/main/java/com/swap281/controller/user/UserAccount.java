package com.swap281.controller.user;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swap281.core.EmailBuilder;
import com.swap281.model.user.User;
import com.swap281.repository.UserAccountRepository;

@RestController
@RequestMapping("/user/account")
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:23333", "http://localhost:8080" })
public class UserAccount {

	@Autowired
	private UserAccountRepository _userRepo;
    
	@Autowired
    private JavaMailSender mailSender;

	@Autowired
	private EmailBuilder mailContentBuilder;
    
	@PostMapping("register")
	private User RegisterNewUser(@RequestBody User newUser) throws Exception
	{
		User user = _userRepo.save(newUser);
		sendEmail(user.username, user.token, user.email);
        System.out.println("\nEmail sent!\n");
		return user;
	}
	
	@GetMapping("username-check/{username}")
	private boolean CheckUsername(@PathVariable("username") String username)
	{
		if(_userRepo.CheckUsername(username) == null)
			return false;
		return true;
	}

	@PutMapping("auth/{username}/{password}")
	private User Auth(@PathVariable String username, @PathVariable String password)
	{
		User user = _userRepo.Auth(username, password);
		return user;
	}
	
	@PutMapping("verify-email/{token}")
	private boolean VerifyEmail(@PathVariable String token)
	{
		try {
			_userRepo.VerifyEmail(UUID.fromString(token));
			return true;
		}catch(Exception e) {
	        System.out.println(e);
			return false;
		}
	}
	
	private void sendEmail(String username, UUID token, String email) throws Exception{
	    MimeMessagePreparator messagePreparator = mimeMessage -> {
	        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
	        messageHelper.setTo(email);
	        messageHelper.setSubject("Swap Account Activation || Swap - Trading Platform for Lehigh");
	        
	        String link = "http://localhost:8080/user/email-verify/" + token.toString();
	        String message = "Click the link to complete account acitivation.";
	        String content = mailContentBuilder.build(username, link, message);
	        messageHelper.setText(content, true);
	    };
	    try {
	        mailSender.send(messagePreparator);
	    } catch (MailException e) {
	        // runtime exception; compiler will not force you to handle it
	    }
    }

}
