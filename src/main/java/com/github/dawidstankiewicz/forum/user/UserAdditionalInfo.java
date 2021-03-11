package com.github.dawidstankiewicz.forum.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

//@Entity
//@Table(name = "user_info")
@Data
@ToString(exclude = "user")
@EqualsAndHashCode(exclude = "user")
public class UserAdditionalInfo {

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

    private Date birthday;

    @Column(length = 20)
    private String city;

    @Column(length = 150)
    private String aboutMe;

    @Column(length = 50)
    private String footer;
}
