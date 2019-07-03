package com.github.dawidstankiewicz.forum.user.activation;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.github.dawidstankiewicz.forum.IntegrationTestCase;
import com.github.dawidstankiewicz.forum.user.User;
import com.github.dawidstankiewicz.forum.user.UserRepository;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ActivationControllerTest extends IntegrationTestCase {

    @Autowired
    private ActivationCodeRepository activationCodeRepository;

    @Autowired
    private UserRepository userRepository;

    private ActivationCode activationCode;

    String URL = "/users/test/activation";

    @Before
    public void setupUser() {
        User user = new User();
        user.setUsername("test");
        user.setEmail("test@test.com");
        user.setPassword("test");

        user = userRepository.save(user);

        activationCode = new ActivationCode("XXX");
        activationCode.setUser(user);

        activationCodeRepository.save(activationCode);
    }

    @Test
    public void testRedirectToConfirmationPage() throws Exception {
        mockMvc
            .perform(get(URL)
                .param("id", activationCode.getId()))
            .andExpect(view().name("activation_success"))
            .andExpect(status().isOk());
    }

    @Test
    public void testPerformInvalidUsernameShouldRedirectToErrorPage() throws Exception {
        String invalidUrl = "/users/invalid/activation";

        mockMvc
            .perform(get(invalidUrl)
                .param("id", activationCode.getId()))
            .andExpect(view().name("error"))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void testPerformInvalidActivationCodeShouldRedirectToErrorPage() throws Exception {
        String url = "/users/test/activation";
        mockMvc
            .perform(get(url)
                .param("id", "invalid"))
            .andExpect(view().name("error"))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void testPerformTwiceShouldRedirectToErrorPage() throws Exception {
        mockMvc
            .perform(get(URL)
                .param("id", activationCode.getId()))
            .andExpect(view().name("activation_success"))
            .andExpect(status().isOk());
        mockMvc
            .perform(get(URL)
                .param("id", activationCode.getId()))
            .andExpect(view().name("error"))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void testShouldDeleteCodeAfterActivation() throws Exception {
        mockMvc
            .perform(get(URL)
                .param("id", activationCode.getId()));

        assertEquals(Optional.empty(), activationCodeRepository.findById(activationCode.getId()));
    }

}
