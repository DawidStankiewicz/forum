/**
 * Created by Dawid Stankiewicz on 3 Jul 2016
 */
package com.github.szczypioreg.forum.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "iduser")
    private int idUser;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "passwordmd5")
    private String passwordMd5;
    
    @Column(name = "role")
    private String role;
    
    public User() {}
    
    public User(int idUser, String email, String username, String passwordMd5, String role) {
        super();
        this.idUser = idUser;
        this.email = email;
        this.username = username;
        this.passwordMd5 = passwordMd5;
        this.role = role;
    }
    
    public int getIdUser() {
        return idUser;
    }
    
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
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
    
    public String getPasswordMd5() {
        return passwordMd5;
    }
    
    public void setPasswordMd5(String passwordMd5) {
        this.passwordMd5 = passwordMd5;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + idUser;
        result = prime * result + ((passwordMd5 == null) ? 0 : passwordMd5.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (idUser != other.idUser)
            return false;
        if (passwordMd5 == null) {
            if (other.passwordMd5 != null)
                return false;
        } else if (!passwordMd5.equals(other.passwordMd5))
            return false;
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "User [idUser=" + idUser + ", email=" + email + ", username=" + username
                + ", passwordMd5=" + passwordMd5 + ", role=" + role + "]";
    }
    
}
