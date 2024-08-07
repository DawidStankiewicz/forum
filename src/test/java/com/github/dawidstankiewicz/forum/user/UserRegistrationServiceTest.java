package com.github.dawidstankiewicz.forum.user;

import com.github.dawidstankiewicz.forum.email.EmailMessageService;
import com.github.dawidstankiewicz.forum.model.dto.UserRegistrationForm;
import com.github.dawidstankiewicz.forum.model.entity.EmailMessage;
import com.github.dawidstankiewicz.forum.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationServiceTest {

    @Mock private UserRepository userRepository;
    @Mock private EmailMessageService emailMessageService;
    @Mock private PasswordEncoder passwordEncoder;
    @Spy @InjectMocks private UserRegistrationService service;

    @Test
    public void shouldRegisterUser() {
        //given
        String email = "email@forum.com";
        UserRegistrationForm form = UserRegistrationForm.builder()
                .email(email)
                .username("username")
                .password("pass")
                .build();
        EmailMessage expectedMessage = EmailMessage.builder()
                .recipient(email)
                .type(EmailMessage.EmailMessageType.CONFIRMATION)
                .build();
        User expectedSavedUser = User.builder()
                .username(form.getUsername())
                .email(email)
                .enabled(false)
                .build();
        //when
        service.registerUser(form);
        //then
        verify(userRepository).save(
                refEq(expectedSavedUser,
                        "emailToken", "createdAt", "password"));
        verify(emailMessageService).scheduleMessage(
                refEq(expectedMessage, "content"));
        verify(passwordEncoder).encode(form.getPassword());
    }
}

