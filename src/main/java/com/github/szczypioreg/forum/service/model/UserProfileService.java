/**
 * Created by Dawid Stankiewicz on 04.08.2016
 */
package com.github.szczypioreg.forum.service.model;

import com.github.szczypioreg.forum.controller.model.UserProfile;


public interface UserProfileService {
    
    public UserProfile findOne(int userId);
    
    public UserProfile findOne(String username);
    
}
