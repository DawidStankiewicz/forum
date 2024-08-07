package com.github.dawidstankiewicz.forum.model.dto;

import com.github.dawidstankiewicz.forum.model.validator.UniqueEmail;
import com.github.dawidstankiewicz.forum.model.validator.UniqueUsername;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationForm {

    @Email(message = "{Email.Invalid}")
    @NotNull(message = "{Email.Empty}")
    @UniqueEmail
    private String email;

    @NotNull(message = "{Field.Required}")
    @Size(min = 8, max = 100, message = "{Password.InvalidSize}")
    private String password;

    @NotNull(message = "{Field.Required}")
    @Size(min = 4, max = 60, message = "{Username.InvalidSize}")
    @UniqueUsername
    private String username;
}
