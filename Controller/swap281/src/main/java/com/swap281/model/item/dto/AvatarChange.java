package com.swap281.model.item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AvatarChange {

    public byte[] avatar;
    
    public Long userId;
    
    public AvatarChange(byte[] avatar, Long userId) {
    	this.avatar = avatar;
    	this.userId = userId;
    }
    
}