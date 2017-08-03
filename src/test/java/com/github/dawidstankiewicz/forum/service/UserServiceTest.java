/**
 * Created by Dawid Stankiewicz on 02.08.2016
 */
package com.github.dawidstankiewicz.forum.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.dawidstankiewicz.forum.ConfigTest;
import com.github.dawidstankiewicz.forum.entity.User;


public class UserServiceTest extends ConfigTest {
    
    @Autowired
    UserService userService;
    
    @Test
    @Transactional
    public void testAddNewUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("user");
        user.setEmail("test@ema.il");
        userService.create(user);

    }
}
