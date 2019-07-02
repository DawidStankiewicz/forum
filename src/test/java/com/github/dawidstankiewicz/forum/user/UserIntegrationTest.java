/**
 * Created by Dawid Stankiewicz on 25.07.2016
 */
package com.github.dawidstankiewicz.forum.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Ignore;
import org.junit.Test;

import com.github.dawidstankiewicz.forum.IntegrationTestCase;


public class UserIntegrationTest extends IntegrationTestCase {
    
    @Test
    @Ignore
    public void testRegisterForm() throws Exception {
        mockMvc.perform(get("/registration")).andExpect(status().isOk()).andExpect(view().name(
                "new_user_form"));
    }
    
    @Test
    public void testNotFoundException() throws Exception {
        mockMvc.perform(get("/user/nas4enfn3saenfasj2knefkjnaskje4nfkjas")).andExpect(status()
                .isNotFound());
    }
}
