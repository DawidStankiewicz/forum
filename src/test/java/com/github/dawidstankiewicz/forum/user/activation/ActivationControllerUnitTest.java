package com.github.dawidstankiewicz.forum.user.activation;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.github.dawidstankiewicz.forum.ControllerUnitTest;
import org.junit.Test;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public class ActivationControllerUnitTest extends ControllerUnitTest {

    @Autowired
    private ActivationController controller;

    @MockBean
    private ActivationService service;

    String URL = "/users/test/activation";

    @Override
    protected Object getControllerForTest() {
        return controller;
    }

    @Test
    public void testShouldCallActivateMethod() throws Exception {
        mockMvc
            .perform(get(URL)
                .param("id", "XXX"))
            .andExpect(view().name("activation_success"))
            .andExpect(status().isOk());

        verify(service, VerificationModeFactory.times(1)).activate("test", "XXX");
    }
}
