package com.github.dawidstankiewicz.forum.section;

import com.github.dawidstankiewicz.forum.security.ForumUserDetailsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(
        controllers = SectionAdminController.class,
        useDefaultFilters = false,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ForumUserDetailsService.class))
@AutoConfigureMockMvc(addFilters = false)
public class SectionAdminControllerMvcTest {

    @MockBean private SectionService sectionService;
    @Autowired private MockMvc mockMvc;

    @Test
    public void shouldReturnSectionPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/a/sections")
                .param("page", "5")
                .param("size", "10")
                .param("sort", "id,desc")   // <-- no space after comma!
                .param("sort", "name,asc")) // <-- no space after comma!
                .andExpect(status().isOk());
    }
}
