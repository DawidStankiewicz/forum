/**
 * Created by Dawid Stankiewicz on 25.07.2016
 */
package com.github.dawidstankiewicz.forum.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.github.dawidstankiewicz.forum.IntegrationTestConfig;
import org.junit.Test;


public class HomeControllerTest extends IntegrationTestConfig {


    @Test
    public void testHomePageView() throws Exception {
        mockMvc.perform(get("/")).andExpect(view().name("home"));
    }

}
