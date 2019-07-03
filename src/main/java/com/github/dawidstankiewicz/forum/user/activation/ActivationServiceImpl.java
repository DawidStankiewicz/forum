package com.github.dawidstankiewicz.forum.user.activation;

import com.github.dawidstankiewicz.forum.exception.ForumException;
import com.github.dawidstankiewicz.forum.exception.ForumException.ErrorCode;
import com.github.dawidstankiewicz.forum.user.Role;
import com.github.dawidstankiewicz.forum.user.User;
import com.github.dawidstankiewicz.forum.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ActivationServiceImpl implements ActivationService {

    @Autowired
    private ActivationCodeRepository activationCodeRepository;

    @Autowired
    private UserService userService;

    @Override
    public void activate(String username, String activationCodeId) {
        ActivationCode activationCode = findActivationCode(activationCodeId);
        validateActivationCode(username, activationCode);
        activateUser(activationCode);
        deleteActivationCode(activationCode);
    }

    private ActivationCode findActivationCode(String id) {
        return activationCodeRepository.findById(id)
            .orElseThrow(() -> new ForumException(ErrorCode.INVALID_ACTIVATION_REQUEST));
    }

    private void validateActivationCode(String username, ActivationCode activationCode) {
        if (isActivationRequestInvalid(activationCode, username)) {
            throw new ForumException(ErrorCode.INVALID_ACTIVATION_REQUEST);
        }
    }

    private boolean isActivationRequestInvalid(ActivationCode activationCode, String username) {
        return activationCode.getUser() == null ||
            !activationCode.getUser().getUsername().equalsIgnoreCase(username);
    }

    private void activateUser(ActivationCode activationCode) {
        User user = activationCode.getUser();
        user.setRole(Role.USER);
        user.setActive(true);
        userService.save(user);
    }

    private void deleteActivationCode(ActivationCode activationCode) {
        activationCodeRepository.delete(activationCode);
    }
}
