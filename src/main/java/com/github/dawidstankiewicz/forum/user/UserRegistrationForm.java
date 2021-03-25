package com.github.dawidstankiewicz.forum.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.dawidstankiewicz.forum.model.entity.User;
import com.github.dawidstankiewicz.forum.model.entity.UserProfile;
import lombok.Data;

@Data
public class UserRegistrationForm {

    private String email;
    private String password;
    private String username;
    private String name;
    private String lastName;

    @JsonIgnore
    public User getNewUserEntity() {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        UserProfile userProfile = new UserProfile();
        userProfile.setName(name);
        userProfile.setLastName(lastName);
        userProfile.setUser(user);

//        user.setInfo(userAdditionalInfo);

        return user;
    }
}
