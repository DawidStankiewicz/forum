/**
 * Created by Dawid Stankiewicz on 05.08.2016
 */
package com.github.szczypioreg.forum.controller.form;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Pattern;

import com.github.szczypioreg.forum.domain.type.Sex;


public class UserEditForm {
    
    @Pattern(regexp = "(?=.*[a-zA-Z]).{0,50}", message = "{Pattern.User.name.validation}")
    private String name;
    
    @Pattern(regexp = "(?=.*[a-zA-Z]).{0,50}", message = "{Pattern.User.name.validation}")
    private String lastName;
    
    private Sex sex;
    
    private String city;
    
    private Date birthday;
    
    private String biography;
    
    @Pattern(regexp = "(?=.*[a-zA-Z]).{0,50}", message = "{Pattern.User.footer.validation}")
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
    
    public Date getBirthday() {
        return birthday;
    }
    
    public void setBirthday(String birthday) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.birthday = format.parse(birthday);
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
