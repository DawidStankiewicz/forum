/**
 * Created by Dawid Stankiewicz on 11.07.2016
 */
package com.github.szczypioreg.forum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.szczypioreg.forum.domain.User;
import com.github.szczypioreg.forum.domain.repository.UserRepository;
import com.github.szczypioreg.forum.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
    
    @Override
    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }
    
    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }
    
    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
    
    @Override
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.add(user);
    }
    
}
