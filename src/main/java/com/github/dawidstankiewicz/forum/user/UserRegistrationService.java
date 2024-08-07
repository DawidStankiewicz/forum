package com.github.dawidstankiewicz.forum.user;

import com.github.dawidstankiewicz.forum.email.EmailMessageService;
import com.github.dawidstankiewicz.forum.model.dto.UserRegistrationForm;
import com.github.dawidstankiewicz.forum.model.entity.EmailMessage;
import com.github.dawidstankiewicz.forum.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailMessageService emailMessageService;

    public UserRegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailMessageService emailMessageService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailMessageService = emailMessageService;
    }

    public User registerUser(UserRegistrationForm form) {
        log.info("Register new user {}, {}", form.getEmail(), form.getUsername());
        String confirmationCode = scheduleConfirmationMessage(form.getEmail());
        User newUser = buildUser(form, confirmationCode);
        return userRepository.save(newUser);
    }

    private String scheduleConfirmationMessage(String email) {
        String randomString = Long.toHexString(Double.doubleToLongBits(Math.random()));
        EmailMessage confirmationMessage = EmailMessage.builder()
                .recipient(email)
                .content(randomString)
                .type(EmailMessage.EmailMessageType.CONFIRMATION)
                .build();
        emailMessageService.scheduleMessage(confirmationMessage);
        return randomString;
    }

    private User buildUser(UserRegistrationForm form, String confirmationCode) {
        return User.builder()
                .email(form.getEmail())
                .emailToken(confirmationCode)
                .username(form.getUsername())
                .createdAt(LocalDateTime.now())
                .enabled(false)
                .password(passwordEncoder.encode(form.getPassword()))
                .build();
    }
}
