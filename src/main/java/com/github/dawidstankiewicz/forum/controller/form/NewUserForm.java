/**
 * Created by Dawid Stankiewicz on 28.07.2016
 */
package com.github.dawidstankiewicz.forum.controller.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


public class NewUserForm {
    
    @Email(message = "{Email.User.email.validation}")
    @NotEmpty(message = "{NotEmpty.User.email.validation}")
    private String email;
    
    @Size(min = 3, max = 50, message = "{Size.User.username.validation}")
    @Pattern(regexp = "[a-zA-Z0-9_\\-]*", message = "{Pattern.User.username.validation}")
    private String username;
    
    @Pattern(regexp = "(.{8,64})",
             message = "{Pattern.User.password.validation}")
    private String password;
    
    public NewUserForm() {}
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
}
