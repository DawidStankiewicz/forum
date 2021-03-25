package com.github.dawidstankiewicz.forum.user.activation;

import com.github.dawidstankiewicz.forum.UnitsTestCase;
import com.github.dawidstankiewicz.forum.exception.ForumException;
import com.github.dawidstankiewicz.forum.model.entity.User;
import com.github.dawidstankiewicz.forum.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.when;

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
        user.setActive(false);

        activationCode = new ActivationCode();
        activationCode.setId("XXX");
        activationCode.setTimestamp(new Date());
        activationCode.setUser(user);

        when(repository.findById("XXX")).thenReturn(Optional.of(activationCode));
    }
}
