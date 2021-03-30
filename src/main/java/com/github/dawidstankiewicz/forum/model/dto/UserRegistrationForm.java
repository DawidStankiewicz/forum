package com.github.dawidstankiewicz.forum.model.dto;

import com.github.dawidstankiewicz.forum.model.validator.UniqueEmail;
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

    //todo add full validation messages
    @Email
    @NotNull
    @UniqueEmail
    private String email;
    @NotNull
    @Size(min = 8, max = 100)
    private String password;
    @NotNull
    @Size(min = 4, max = 60)
    private String username;
}
