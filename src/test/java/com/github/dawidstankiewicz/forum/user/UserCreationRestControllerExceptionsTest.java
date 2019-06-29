package com.github.dawidstankiewicz.forum.user;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dawidstankiewicz.forum.IntegrationsTestCase;
import com.github.dawidstankiewicz.forum.user.activation.ActivationCodeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;

@Rollback
public class UserCreationRestControllerExceptionsTest extends IntegrationsTestCase {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivationCodeService activationCodeService;

    private MvcResult mvcResult;

    private String API_ENDPOINT_URL = "/api/users";

    @Test
    public void testShouldRespondInvalidRequestError() throws Exception {
        performRequestToCreateUser("");
        assertEquals(400, mvcResult.getResponse().getStatus());
        // todo check custom error code
    }

    private String parseToJson(Object object) {
        String json = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            System.err.println(e);
        }
        return json;
    }

    private void performRequestToCreateUser(String jsonBody) throws Exception {
        mvcResult =
            mockMvc
                .perform(post(API_ENDPOINT_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonBody)).andReturn();
    }
}
