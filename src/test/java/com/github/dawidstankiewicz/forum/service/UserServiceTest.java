/**
 * Created by Dawid Stankiewicz on 02.08.2016
 */
package com.github.dawidstankiewicz.forum.service;

import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.dawidstankiewicz.forum.IntegrationTestConfig;


public class UserServiceTest extends IntegrationTestConfig {
    
    @Autowired
    UserService userService;

}
