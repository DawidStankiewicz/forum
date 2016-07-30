/**
 * Created by Dawid Stankiewicz on 3 Jul 2016
 */
package com.github.szczypioreg.forum.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class User implements Serializable {
    
    private static final long serialVersionUID = 893284018826505486L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "active")
    private boolean active;
    
    @Column(name = "idprofilepicture")
    private int idProfilePicture;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "rolesofusers",
               joinColumns = @JoinColumn(name = "iduser", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "idrole", referencedColumnName = "id"))
    private Set<Role> roles;
    
    public User() {}
    
    public User(int idUser, String email, String username, String password, boolean active,
            int idProfilePicture, Set<Role> roles) {
        super();
        this.idUser = idUser;
        this.email = email;
        this.username = username;
        this.password = password;
        this.active = active;
        this.idProfilePicture = idProfilePicture;
        this.roles = roles;
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
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public int getIdProfilePicture() {
        return idProfilePicture;
    }
    
    public void setIdProfilePicture(int idProfilePicture) {
        this.idProfilePicture = idProfilePicture;
    }
    
    public Set<Role> getRoles() {
        return roles;
    }
    
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (active ? 1231 : 1237);
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + idProfilePicture;
        result = prime * result + idUser;
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (active != other.active) {
            return false;
        }
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }
        if (idProfilePicture != other.idProfilePicture) {
            return false;
        }
        if (idUser != other.idUser) {
            return false;
        }
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
            return false;
        }
        if (roles == null) {
            if (other.roles != null) {
                return false;
            }
        } else if (!roles.equals(other.roles)) {
            return false;
        }
        if (username == null) {
            if (other.username != null) {
                return false;
            }
        } else if (!username.equals(other.username)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "User [idUser=" + idUser + ", email=" + email + ", username=" + username
                + ", password=" + password + ", active=" + active + ", idProfilePicture="
                + idProfilePicture + ", roles=" + roles + "]";
    }
    
}
