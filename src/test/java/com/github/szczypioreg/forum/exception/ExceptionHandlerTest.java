/**
 * Created by Dawid Stankiewicz on 29.07.2016
 */
package com.github.szczypioreg.forum.exception;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

import com.github.szczypioreg.forum.ConfigTest;


public class ExceptionHandlerTest extends ConfigTest {
    
    @Test
    public void notFoundErrorPage() throws Exception {
        mockMvc.perform(get("/fsasa4eraw4rffsref")).andExpect(status().isNotFound());
    }
}
