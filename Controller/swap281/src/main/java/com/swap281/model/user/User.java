package com.swap281.model.user;

 
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "username")
    public String username;
    
    @Column(name = "password")
    public String password;
    
    @Column(name = "email")
    public String  email;
    
    @Column(name = "contact") 
    public String contact;
    
    @Column(columnDefinition = "integer default 1", nullable=false)
    public Long privileged_group_id = (long) 1;
    
    @Column(name = "first_name")
    public String first_name;
    
    @Column(name = "last_name")
    public String last_name;
    
    @Column(name = "full_name")
    public String full_name = first_name + ' ' + last_name;
    
    @Column(name = "token")
    public UUID token = UUID.randomUUID();
    
    @Column(name = "create_date")
    public Date create_date = new Date();

    @Column(name = "is_verified")
    public Boolean is_verified = false;
    
    @Column(name = "avatar")
    public byte[] avatar = null;

    public User(Long id, String username, String password, String email, 
            Long privileged_group_id, String first_name, String last_name, String full_name, UUID token,
            Date create_date, Boolean is_verified, byte[] avatar)
    {
    	this.id = id;
    	this.username = username;
    	this.password = password;
    	this.email = email;
    	this.privileged_group_id = privileged_group_id;
    	this.first_name = first_name;
    	this.last_name = last_name;
    	this.full_name = full_name;
        this.token = token;
        this.create_date = create_date;
        this.is_verified = is_verified;
        this.avatar = avatar;
    }
    
    public User(Long id, String name) {
    	this.id = id;
    	this.username = name;
    }    
    public User() {
    	
    }
}
