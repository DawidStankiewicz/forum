package com.github.dawidstankiewicz.forum.user;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import com.github.dawidstankiewicz.forum.UnitsTestCase;
import com.github.dawidstankiewicz.forum.user.activation.ActivationService;
import org.junit.Test;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserCreationServiceTest extends UnitsTestCase {

    @Autowired
    private UserCreationService service;

    @MockBean
    private UserService userService;

    @MockBean
    private ActivationService activationService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void testShouldCallSaveInUserService() {
        callCreateNewUser();

        verify(
            userService,
            VerificationModeFactory.times(1))
            .save(any(User.class));
    }

    @Test
    public void testShouldSendActivationCode() {
        callCreateNewUser();

        verify(
            activationService,
            VerificationModeFactory.times(1))
            .sendActivationCode(any(User.class));
    }

    @Test
    public void testShouldEncodePassword() {
        callCreateNewUser();

        verify(
            passwordEncoder,
            VerificationModeFactory.times(1))
            .encode("password");
    }

    private void callCreateNewUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("password");

        service.create(user);
    }

}
