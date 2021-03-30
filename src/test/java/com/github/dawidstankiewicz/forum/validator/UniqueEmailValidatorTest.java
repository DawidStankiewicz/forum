package com.github.dawidstankiewicz.forum.validator;

import com.github.dawidstankiewicz.forum.model.entity.User;
import com.github.dawidstankiewicz.forum.model.validator.UniqueEmailValidator;
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
public class UniqueEmailValidatorTest {

    @Mock private UserRepository userRepository;
    @Mock private ConstraintValidatorContext constraintValidatorContext;
    @Spy @InjectMocks private UniqueEmailValidator validator;

    @Test
    public void shouldValidateUniqueEmail() {
        //given
        String email = "email";
        doReturn(null).when(userRepository).findByEmail(any());
        //when
        boolean result = validator.isValid(email, constraintValidatorContext);
        //then
        assertTrue(result);
        verify(userRepository).findByEmail(email);
    }

    @Test
    public void shouldInvalidateUsedEmail() {
        //given
        String email = "email";
        doReturn(User.builder().email(email).build()).when(userRepository).findByEmail(any());
        //when
        boolean result = validator.isValid(email, constraintValidatorContext);
        //then
        assertFalse(result);
        verify(userRepository).findByEmail(email);
    }
}

