package com.github.dawidstankiewicz.forum.model.validator;

import com.github.dawidstankiewicz.forum.model.entity.User;
import com.github.dawidstankiewicz.forum.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired private UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        User user = userRepository.findByUsername(email);
        return user == null;
    }
}
