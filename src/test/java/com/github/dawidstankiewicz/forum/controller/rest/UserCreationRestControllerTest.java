package com.github.dawidstankiewicz.forum.controller.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dawidstankiewicz.forum.IntegrationTestConfig;
import com.github.dawidstankiewicz.forum.entity.ActivationCode;
import com.github.dawidstankiewicz.forum.form.UserCreationForm;
import com.github.dawidstankiewicz.forum.service.ActivationCodeService;
import com.github.dawidstankiewicz.forum.service.UserService;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Dawid Stankiewicz on 08.08.2017.
 */

public class UserCreationRestControllerTest extends IntegrationTestConfig {

    private static final Logger LOGGER = LoggerFactory
        .getLogger(UserCreationRestControllerTest.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ActivationCodeService activationCodeService;

    private MvcResult mvcResult;


    @Before
    public void setup() throws Exception {
        performRequest();
    }

    @Test
    @Rollback
    public void testCreateStatus() throws Exception {
        assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    @Rollback
    public void testCreateUserEntity() throws Exception {
        assertNotNull(userService.findByUsername("test"));
        assertEquals(false, userService.findByUsername("test").isActive());
    }

    @Test
    @Rollback
    public void testEncodePasswordOnCreatin() throws Exception {
        assertEquals(60, userService.findByUsername("test").getPassword().length());
    }

    @Test
    @Rollback
    public void testSaveActivationCode() throws Exception {
        ActivationCode activationCode = activationCodeService.findActivationCodeByUsername("test");
        assertNotNull(activationCode);
        assertEquals(64, activationCode.getId().length());
    }

    @Test
    public void testSendEmailWithActivationCode() {
        assertTrue(testSmtp.waitForIncomingEmail(4000, 1));
    }

    private void performRequest() throws Exception {
        String url = "/api/users";
        String jsonBody = getUserCreationFormJsonBody();

        mvcResult =
            mockMvc
                .perform(post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonBody)).andReturn();
    }

    private String getUserCreationFormJsonBody() throws JsonProcessingException {
        UserCreationForm userCreationForm = new UserCreationForm();
        userCreationForm.setEmail("test@forum.com");
        userCreationForm.setUsername("test");
        userCreationForm.setPassword("testtest");
        userCreationForm.setName("Test");
        userCreationForm.setLastName("Test");
        userCreationForm.setMale(false);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(userCreationForm);

        return jsonBody;
    }
}
