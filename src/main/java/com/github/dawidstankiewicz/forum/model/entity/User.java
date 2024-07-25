package com.github.dawidstankiewicz.forum.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(unique = true)
    private String secondaryEmail;

    @Column(unique = true)
    private String emailToken;

    @Column(nullable = false, unique = true, length = 60)
    private String username;

    @Column(length = 60)
    private String password;

    private boolean enabled;

    private boolean removed;

    private LocalDateTime createdAt;

    private LocalDateTime lastLoginTime;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToOne(mappedBy = "user")
    private UserProfile info;
}
