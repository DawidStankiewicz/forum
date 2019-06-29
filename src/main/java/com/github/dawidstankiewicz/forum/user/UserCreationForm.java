package com.github.dawidstankiewicz.forum.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Data
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

    private Gender gender;

    @JsonIgnore
    public User getNewUserEntity() {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setGender(gender);

        UserAdditionalInfo userAdditionalInfo = new UserAdditionalInfo();
        userAdditionalInfo.setName(name);
        userAdditionalInfo.setLastName(lastName);
        userAdditionalInfo.setUser(user);

        user.setInfo(userAdditionalInfo);

        return user;
    }
}
