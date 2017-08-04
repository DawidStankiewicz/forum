package com.github.dawidstankiewicz.forum.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

/**
 * Created by Dawid Stankiewicz on 03.08.2017.
 */

@Entity
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 398881966654252337L;

    @Id
    @Column(name = "user")
    private int id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;

    @Column(length = 15)
    private String phone;

    @Column(length = 20)
    private String name;

    @Column(length = 30)
    private String lastName;

    @Column
    private Date birthday;

    @Column(nullable = false)
    private boolean male;

    @Column(length = 20)
    private String city;

    @Column(length = 150)
    private String aboutMe;

    @Column(length = 50)
    private String footer;

    @Column
    private Date joinedDate;

    @Column
    private Date lastLoginTime;

    @Column
    private boolean removed;

    @Column
    private int posts;

    @Column
    private int topics;

    public UserInfo() {
    }

    @PrePersist
    protected void onCreate() {
        this.joinedDate = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public int getPosts() {
        return posts;
    }

    public void setPosts(int posts) {
        this.posts = posts;
    }

    public int getTopics() {
        return topics;
    }

    public void setTopics(int topics) {
        this.topics = topics;
    }
}
