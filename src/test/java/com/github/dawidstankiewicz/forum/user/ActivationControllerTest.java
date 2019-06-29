package com.github.dawidstankiewicz.forum.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.dawidstankiewicz.forum.IntegrationsTestCase;
import com.github.dawidstankiewicz.forum.user.activation.ActivationCode;
import com.github.dawidstankiewicz.forum.user.activation.ActivationCodeService;
import com.github.dawidstankiewicz.forum.user.activation.ActivationService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class ActivationControllerTest extends IntegrationsTestCase {

    @Autowired
    private ActivationService activationService;

    @Autowired
    private ActivationCodeService activationCodeService;

    @Before
    public void setupUser() {
        // todo register new user and get activation code
        User user = new User();
        user.setUsername("test");
        user.setEmail("test@test.com");
        user.setPassword("test");

        activationService.sendActivationCode(user);

    }

    @Test
    public void testRedirectToLoginPage() throws Exception {

        ActivationCode activationCode = activationCodeService.findActivationCodeByUsername("test");

        String url = "/users/test/activation";
        mockMvc
            .perform(get(url)
                .param("id", activationCode.getId()))
            .andExpect(status()
                .is3xxRedirection())
            .andExpect(redirectedUrl("/login"));
    }

}
