package com.github.dawidstankiewicz.forum;


import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public abstract class ControllerUnitTest {

    protected MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(getControllerForTest()).build();
    }

    @Test
    public void testConfig() {
        assertNotNull(mockMvc);
        assertNotNull(getControllerForTest());
    }

    protected abstract Object getControllerForTest();
}
