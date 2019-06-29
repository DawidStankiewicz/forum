package com.github.dawidstankiewicz.forum.user;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Entity
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 893284018826505486L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 30, nullable = false, unique = true)
    private String username;

    @Column(length = 60, nullable = false)
    private String password;

    private boolean active;

    private boolean removed;

    private Date createdAt;

    private Date lastLoginTime;

    private Gender gender;

    private Role role = Role.UNDEFINED;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserAdditionalInfo info;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
}
