/**
 * Created by Dawid Stankiewicz on 22.07.2016
 */
package com.github.szczypioreg.forum.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
    
    @Id
    @Column(name = "idrole", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRole;
    
    @Column(name = "name", unique = true)
    private String name;
    
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    private Set<User> users;
    
    public Role() {}
    
    public Role(String name) {
        super();
        this.name = name;
    }
    
    public int getIdRole() {
        return idRole;
    }
    
    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Set<User> getUsers() {
        return users;
    }
    
    public void setUsers(Set<User> users) {
        this.users = users;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idRole;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Role other = (Role) obj;
        if (idRole != other.idRole)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (users == null) {
            if (other.users != null)
                return false;
        } else if (!users.equals(other.users))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "Role [idRole=" + idRole + ", name=" + name + ", users=" + users + "]";
    }
    
}
