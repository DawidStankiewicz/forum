package com.github.dawidstankiewicz.forum.user;

import com.github.dawidstankiewicz.forum.email.EmailMessageService;
import com.github.dawidstankiewicz.forum.model.entity.EmailMessage;
import com.github.dawidstankiewicz.forum.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserRegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired private EmailMessageService emailMessageService;

    public void create(User user) {
//        userService.save(prepareUser(user));
    }

    private User prepareUser(User user) {
        user.setPassword(getEncodedPassword(user.getPassword()));
        user.setEnabled(false);
        return user;
    }

    private String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void registerEmailAndSendConfirmationMessage(String email) {
        String confirmationCode = scheduleConfirmationMessage(email);
        saveNewUser(email, confirmationCode);
    }

    private void saveNewUser(String email, String confirmationCode) {
        User newUser = User.builder()
                .email(email)
                .createdAt(LocalDateTime.now())
                .enabled(false)
                .build();
        userRepository.save(newUser);
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
}
