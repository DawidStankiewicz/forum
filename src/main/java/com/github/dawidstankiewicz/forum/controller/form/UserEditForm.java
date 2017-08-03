/**
 * Created by Dawid Stankiewicz on 05.08.2016
 */
package com.github.dawidstankiewicz.forum.controller.form;

import java.text.ParseException;

import javax.validation.constraints.Pattern;

import com.github.dawidstankiewicz.forum.entity.type.Sex;


public class UserEditForm {
    
    @Pattern(regexp = "^\\p{IsAlphabetic}*$", message = "{Pattern.User.name.validation}")
    private String name;
    
    @Pattern(regexp = "^\\p{IsAlphabetic}*$", message = "{Pattern.User.name.validation}")
    private String lastName;
    
    private Sex sex;
    
    private String city;
    
    private String birthday;
    
    private String biography;
    
    private String footer;
    
    public UserEditForm() {}
    
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
    
    public Sex getSex() {
        return sex;
    }
    
    public void setSex(Sex sex) {
        this.sex = sex;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getBirthday() {
        return birthday;
    }
    
    public void setBirthday(String birthday) throws ParseException {
        this.birthday = birthday;
    }
    
    public String getBiography() {
        return biography;
    }
    
    public void setBiography(String biography) {
        this.biography = biography;
    }
    
    public String getFooter() {
        return footer;
    }
    
    public void setFooter(String footer) {
        this.footer = footer;
    }
    
}
