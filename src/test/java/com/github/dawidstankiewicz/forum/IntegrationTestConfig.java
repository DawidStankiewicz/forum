/**
 * Created by Dawid Stankiewicz on 30.07.2016
 */
package com.github.dawidstankiewicz.forum;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class IntegrationTestConfig {

    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mockMvc;

    protected GreenMail testSmtp;

    @Before
    public void init() {
        initSmtp();
        mockMvc = webAppContextSetup(context).build();
    }

    @Test
    public void testConfig() {

    }

    @After
    public void cleanup() {
        testSmtp.stop();
    }

    private void initSmtp() {
        int port = 10025;
        String bindAddress = "localhost";
        String protocol = "smtp";
        ServerSetup setup = new ServerSetup(port, bindAddress, protocol);
        testSmtp = new GreenMail(setup);
        testSmtp.start();
    }
}
