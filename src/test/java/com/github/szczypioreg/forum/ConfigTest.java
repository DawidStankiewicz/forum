/**
 * Created by Dawid Stankiewicz on 30.07.2016
 */
package com.github.szczypioreg.forum;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.github.szczypioreg.forum.config.JPAConfig;
import com.github.szczypioreg.forum.config.RootConfig;
import com.github.szczypioreg.forum.config.ThymeleafConfig;
import com.github.szczypioreg.forum.config.WebConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { RootConfig.class,
                                  WebConfig.class,
                                  ThymeleafConfig.class,
                                  JPAConfig.class })
@Ignore()
public class ConfigTest {
    
    @Autowired
    protected WebApplicationContext context;
    
    protected MockMvc mockMvc;
    
    @Before
    public void init() {
        mockMvc = webAppContextSetup(context).build();
    }
    
}
