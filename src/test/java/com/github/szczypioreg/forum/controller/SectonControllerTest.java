/**
 * Created by Dawid Stankiewicz on 30.07.2016
 */
package com.github.szczypioreg.forum.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.github.szczypioreg.forum.ConfigTest;
import com.github.szczypioreg.forum.controller.form.NewSectionForm;


public class SectonControllerTest extends ConfigTest {
    
    @Test
    @Transactional
    public void testNewSectionFunction() throws Exception {
        NewSectionForm newSection = new NewSectionForm();
        newSection.setName("abc123abc");
        mockMvc.perform(post("/section/new").flashAttr("newSection", newSection)).andExpect(status()
                .is3xxRedirection());
    }
}
