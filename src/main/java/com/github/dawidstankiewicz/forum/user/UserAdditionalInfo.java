package com.github.dawidstankiewicz.forum.user;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "user")
@EqualsAndHashCode(exclude = "user")
public class UserAdditionalInfo implements Serializable {

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

    private Date birthday;

    @Column(length = 20)
    private String city;

    @Column(length = 150)
    private String aboutMe;

    @Column(length = 50)
    private String footer;
}
