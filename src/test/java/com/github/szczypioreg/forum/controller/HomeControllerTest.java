/**
 * Created by Dawid Stankiewicz on 25.07.2016
 */
package com.github.szczypioreg.forum.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import com.github.szczypioreg.forum.ConfigTest;


public class HomeControllerTest extends ConfigTest {
    
    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/")).andExpect(view().name("home")).andExpect(model().attributeExists(
                "sections")).andExpect(model().attributeExists("topics")).andExpect(model()
                        .attributeExists("posts"));
    }
    
}
