/**
 * Created by Dawid Stankiewicz on 25.07.2016
 */
package com.github.szczypioreg.forum;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


public class LoginPageTest extends ConfigTest {
    
    @Autowired
    private FilterChainProxy springSecurityFilterChain;
    
    @Before
    public void setup() {
        this.mockMvc =
            MockMvcBuilders.webAppContextSetup(this.context).addFilter(
                    this.springSecurityFilterChain).build();
    }
    
    @Test
    public void testLoginView() throws Exception {
        mockMvc.perform(get("/login")).andExpect(view().name("login"));
    }
    
    @Test
    public void testLogin() throws Exception {
        
        this.mockMvc.perform(post("/login").param("username", "Admin").param("password", "admin")
                .with(csrf())).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/"))
                .andExpect(authenticated());
    }
    
}
