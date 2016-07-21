/**
 * Created by Dawid Stankiewicz on 11.07.2016
 */
package com.github.szczypioreg.forum.service;

import java.util.List;

import com.github.szczypioreg.forum.domain.User;

public interface UserService {
    List<User> findAll();
    
    User getUserByIdUser(int id);
    
    User getUserByUsername(String username);
    
    User getUserByEmail(String email);
    
    void add(User user);
}
