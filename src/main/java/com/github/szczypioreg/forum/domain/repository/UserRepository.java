/**
 * Created by Dawid Stankiewicz on 3 Jul 2016
 */
package com.github.szczypioreg.forum.domain.repository;

import java.util.List;

import com.github.szczypioreg.forum.domain.User;

public interface UserRepository {
    List<User> getAllUsers();
    
    User getUserById(int id);
    
    User getUserByUsername(String username);
    
    User getUserByEmail(String email);
    
    void add(User user);
}
