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
    
    public User(int idUser, String email, String name, String passwordMd5, String type) {
        super();
        this.idUser = idUser;
        this.email = email;
        this.username = name;
        this.passwordMd5 = passwordMd5;
        this.role = type;
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
    
    public String getName() {
        return username;
    }
    
    public void setName(String name) {
        this.username = name;
    }
    
    public String getPasswordMd5() {
        return passwordMd5;
    }
    
    public void setPasswordMd5(String passwordMd5) {
        this.passwordMd5 = passwordMd5;
    }
    
    public String getType() {
        return role;
    }
    
    public void setType(String type) {
        this.role = type;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + idUser;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((passwordMd5 == null) ? 0 : passwordMd5.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
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
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
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
        return true;
    }
}
