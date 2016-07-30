/**
 * Created by Dawid Stankiewicz on 29.07.2016
 */
package com.github.szczypioreg.forum.exception;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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

import com.github.szczypioreg.forum.ConfigTest;
import com.github.szczypioreg.forum.config.RootConfig;
import com.github.szczypioreg.forum.config.ThymeleafConfig;
import com.github.szczypioreg.forum.config.WebConfig;


public class ExceptionHandlerTest extends ConfigTest {
    
    @Test
    public void notFoundErrorPage() throws Exception {
        mockMvc.perform(get("/fsasa4eraw4rffsref")).andExpect(status().isNotFound());
    }
}
