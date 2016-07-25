/**
 * Created by Dawid Stankiewicz on 25.07.2016
 */
package com.github.szczypioreg.forum.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.github.szczypioreg.forum.config.RootConfig;
import com.github.szczypioreg.forum.config.ThymeleafConfig;
import com.github.szczypioreg.forum.config.WebConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { RootConfig.class,
                                  WebConfig.class,
                                  ThymeleafConfig.class })
public class HomeControllerTest {
    
    @Autowired
    private WebApplicationContext context;
    
    private MockMvc mockMvc;
    
    @Before
    public void init() {
        mockMvc = webAppContextSetup(this.context).build();
    }
    
    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/")).andExpect(view().name("home")).andExpect(model().attributeExists(
                "sections")).andExpect(model().attributeExists("topics")).andExpect(model()
                        .attributeExists("posts"));
    }
    
}
