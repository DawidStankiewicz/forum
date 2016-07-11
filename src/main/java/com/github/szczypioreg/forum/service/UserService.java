/**
 * Created by Dawid Stankiewicz on 11.07.2016
 */
package com.github.szczypioreg.forum.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.szczypioreg.forum.domain.User;

public interface UserService {
    List<User> getAllUsers();
    
    User getUserById(int id);
    
    void add(User user);
}
