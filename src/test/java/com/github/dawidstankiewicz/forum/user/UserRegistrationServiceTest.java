package com.github.dawidstankiewicz.forum.user;

import com.github.dawidstankiewicz.forum.email.EmailMessageService;
import com.github.dawidstankiewicz.forum.model.entity.EmailMessage;
import com.github.dawidstankiewicz.forum.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationServiceTest {

    @Mock private UserRepository userRepository;
    @Mock private EmailMessageService emailMessageService;
    @Spy @InjectMocks private UserRegistrationService service;


    @Test
    public void shoulRegisterEmail() {
        //given
        String email = "email@forum.com";
        //when
        service.registerEmailAndSendConfirmationMessage(email);
        //then
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void shouldSendConfirmationEmail() {
        //given
        String email = "email@forum.com";
        EmailMessage expectedMessage = EmailMessage.builder()
                .recipient(email)
                .type(EmailMessage.EmailMessageType.CONFIRMATION)
                .build();
        //when
        service.registerEmailAndSendConfirmationMessage(email);
        //then
        verify(emailMessageService).scheduleMessage(
                refEq(expectedMessage, "content"));
    }
}

