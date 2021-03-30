package com.github.dawidstankiewicz.forum.validator;

import com.github.dawidstankiewicz.forum.model.entity.User;
import com.github.dawidstankiewicz.forum.model.validator.UniqueEmailValidator;
import com.github.dawidstankiewicz.forum.model.validator.UniqueUsernameValidator;
import com.github.dawidstankiewicz.forum.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintValidatorContext;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UniqueUsernameValidatorTest {

    @Mock private UserRepository userRepository;
    @Mock private ConstraintValidatorContext constraintValidatorContext;
    @Spy @InjectMocks private UniqueUsernameValidator validator;

    @Test
    public void shouldValidateUniqueUsername() {
        //given
        String username = "username";
        doReturn(null).when(userRepository).findByUsername(any());
        //when
        boolean result = validator.isValid(username, constraintValidatorContext);
        //then
        assertTrue(result);
        verify(userRepository).findByUsername(username);
    }

    @Test
    public void shouldInvalidateUsedUsername() {
        //given
        String username = "username";
        doReturn(User.builder().username(username).build()).when(userRepository).findByUsername(any());
        //when
        boolean result = validator.isValid(username, constraintValidatorContext);
        //then
        assertFalse(result);
        verify(userRepository).findByUsername(username);
    }
}

