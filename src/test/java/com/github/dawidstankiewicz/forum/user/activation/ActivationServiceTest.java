package com.github.dawidstankiewicz.forum.user.activation;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.dawidstankiewicz.forum.UnitsTestCase;
import com.github.dawidstankiewicz.forum.exception.ForumException;
import com.github.dawidstankiewicz.forum.exception.ForumException.ErrorCode;
import com.github.dawidstankiewicz.forum.user.Role;
import com.github.dawidstankiewicz.forum.user.User;
import com.github.dawidstankiewicz.forum.user.UserService;
import java.util.Date;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public class ActivationServiceTest extends UnitsTestCase {

    @Autowired
    private ActivationService service;

    @MockBean
    private ActivationCodeRepository repository;

    @MockBean
    private UserService userService;

    private User user;
    private ActivationCode activationCode;

    @Before
    public void setup() {
        user = new User();
        user.setId(123);
        user.setEmail("test@forum.com");
        user.setUsername("test");
        user.setActive(false);

        activationCode = new ActivationCode();
        activationCode.setId("XXX");
        activationCode.setTimestamp(new Date());
        activationCode.setUser(user);

        when(repository.findById("XXX")).thenReturn(Optional.of(activationCode));
    }

    @Test
    public void testShouldActivate() {
        User expectedUser = new User();
        expectedUser.setId(user.getId());
        expectedUser.setEmail(user.getEmail());
        expectedUser.setUsername(user.getUsername());
        expectedUser.setActive(true);
        expectedUser.setRole(Role.USER);

        service.activate(user.getUsername(), activationCode.getId());

        verify(
            userService,
            VerificationModeFactory.times(1))
            .save(expectedUser);

        verify(
            repository,
            VerificationModeFactory.times(1))
            .delete(activationCode);
    }

    @Test
    public void testNotFoundActivationCodeShouldThrowException() {
        try {
            service.activate(user.getUsername(), "INVALID");
            fail("Should throw exception - activation code is invalid");
        } catch (Exception e) {
            assertTrue(e instanceof ForumException);
            assertEquals(ErrorCode.INVALID_ACTIVATION_REQUEST, ((ForumException) e).getErrorCode());
        }
    }

    @Test
    public void testInvalidUsernameShouldThrowException() {
        try {
            service.activate("INVALID", activationCode.getId());
            fail("Should throw exception - username is invalid");
        } catch (Exception e) {
            assertTrue(e instanceof ForumException);
            assertEquals(ErrorCode.INVALID_ACTIVATION_REQUEST, ((ForumException) e).getErrorCode());
        }
    }
}
