package com.github.dawidstankiewicz.forum.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.dawidstankiewicz.forum.entity.User;
import com.github.dawidstankiewicz.forum.entity.UserInfo;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Dawid Stankiewicz on 04.08.2017.
 */
public class UserCreationForm {

    @Email(message = "{Email.User.email.validation}")
    @NotEmpty(message = "{NotEmpty.User.email.validation}")
    private String email;

    @Pattern(regexp = "(.{8,64})",
        message = "{Pattern.User.password.validation}")
    private String password;

    @Size(min = 3, max = 50, message = "{Size.User.username.validation}")
    @Pattern(regexp = "[a-zA-Z0-9_\\-]*", message = "{Pattern.User.username.validation}")
    private String username;

    @Size(min = 2, max = 20)
    private String name;

    @Size(min = 2, max = 30)
    private String lastName;

    private boolean male;

    public UserCreationForm() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    @JsonIgnore
    public User getNewUserEntity() {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);

        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setLastName(lastName);
        userInfo.setMale(male);
        userInfo.setUser(user);

        user.setInfo(userInfo);

        return user;
    }
}
