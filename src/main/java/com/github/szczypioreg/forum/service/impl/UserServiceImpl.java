/**
 * Created by Dawid Stankiewicz on 11.07.2016
 */
package com.github.szczypioreg.forum.service.impl;

import com.github.szczypioreg.forum.exception.UserNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.szczypioreg.forum.controller.form.UserEditForm;
import com.github.szczypioreg.forum.domain.Role;
import com.github.szczypioreg.forum.domain.User;
import com.github.szczypioreg.forum.domain.repository.UserRepository;
import com.github.szczypioreg.forum.exception.IncorrectPasswordException;
import com.github.szczypioreg.forum.service.RoleService;
import com.github.szczypioreg.forum.service.UserService;


@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleService roleService;
    
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    @Override
    public User findOne(int id) {
        return userRepository.findOne(id);
    }
    
    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }
    
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
    
    @Override
    public void create(User user) {
        Set<Role> roles = new HashSet<>();
        Role role = roleService.findByName("USER");
        roles.add(role);
        user.setRoles(roles);
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        userRepository.save(user);
    }
    
    @Override
    public void remove(int id) {
        remove(findOne(id));
    }
    
    @Override
    public void remove(User user) {
        userRepository.delete(user);
    }
    
    @Override
    public void remove(User user,
                       String password) {
        if (!passwordEncoder.matches(password, user.getPassword())) {
            System.out.println("user: " + user.getPassword() + " pass: " + passwordEncoder.encode(
                    password));
            throw new IncorrectPasswordException();
        }
        userRepository.delete(user);
    }
    
    @Override
    public void save(User user,
                     UserEditForm userEditForm) {
        if (!userEditForm.getBirthday().isEmpty()) {
            DateFormat fromat = new SimpleDateFormat("yyyy-mm-dd");
            try {
                user.setBirthday(fromat.parse(userEditForm.getBirthday()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            user.setBirthday(null);
        }
        System.out.println(userEditForm.getBirthday());
        user.setName(userEditForm.getName() != null ? userEditForm.getName() : null);
        user.setLastName(userEditForm.getLastName());
        user.setSex(userEditForm.getSex());
        user.setCity(userEditForm.getCity());
        user.setFooter(userEditForm.getFooter());
        user.setBiography(userEditForm.getBiography());
        save(user);
    }
    
}
