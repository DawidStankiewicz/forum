/**
 * Created by Dawid Stankiewicz on 3 Jul 2016
 */
package com.github.szczypioreg.forum.domain;

import java.io.Serializable;
import java.util.Date;
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
    private int id;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "second_email")
    private String secondEmail;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "id_profile_picture")
    private int idProfilePicture;
    
    @Column(name = "id_background_picture")
    private int idBackgroundPicture;
    
    @Column(name = "sex")
    private String sex;
    
    @Column(name = "birthday")
    private Date birthday;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "biography")
    private String biography;
    
    @Column(name = "footer")
    private String footer;
    
    @Column(name = "register_date")
    private Date registerDate;
    
    @Column(name = "is_email_verifed")
    private boolean emailVerifed;
    
    @Column(name = "last_login_date")
    private Date lastLoginDate;
    
    @Column(name = "is_active")
    private boolean active;
    
    @Column(name = "closing_date")
    private Date closingDate;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "rolesofusers",
               joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id"))
    private Set<Role> roles;
    
    public User() {}
    
    public User(String email,
                String secondEmail,
                String username,
                String password,
                String phone,
                String name,
                String lastName,
                int idProfilePicture,
                int idBackgroundPicture,
                String sex,
                Date birthday,
                String city,
                String biography,
                String footer,
                Date registerDate,
                boolean emailVerifed,
                Date lastLoginDate,
                boolean active,
                Date closingDate,
                Set<Role> roles) {
        this.email = email;
        this.secondEmail = secondEmail;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.name = name;
        this.lastName = lastName;
        this.idProfilePicture = idProfilePicture;
        this.idBackgroundPicture = idBackgroundPicture;
        this.sex = sex;
        this.birthday = birthday;
        this.city = city;
        this.biography = biography;
        this.footer = footer;
        this.registerDate = registerDate;
        this.emailVerifed = emailVerifed;
        this.lastLoginDate = lastLoginDate;
        this.active = active;
        this.closingDate = closingDate;
        this.roles = roles;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSecondEmail() {
        return secondEmail;
    }
    
    public void setSecondEmail(String secondEmail) {
        this.secondEmail = secondEmail;
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
    
    public int getIdProfilePicture() {
        return idProfilePicture;
    }
    
    public void setIdProfilePicture(int idProfilePicture) {
        this.idProfilePicture = idProfilePicture;
    }
    
    public int getIdBackgroundPicture() {
        return idBackgroundPicture;
    }
    
    public void setIdBackgroundPicture(int idBackgroundPicture) {
        this.idBackgroundPicture = idBackgroundPicture;
    }
    
    public String getSex() {
        return sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public Date getBirthday() {
        return birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
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
    
    public Date getRegisterDate() {
        return registerDate;
    }
    
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
    
    public boolean isEmailVerifed() {
        return emailVerifed;
    }
    
    public void setEmailVerifed(boolean emailVerifed) {
        this.emailVerifed = emailVerifed;
    }
    
    public Date getLastLoginDate() {
        return lastLoginDate;
    }
    
    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public Date getClosingDate() {
        return closingDate;
    }
    
    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
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
        result = prime * result + ((biography == null) ? 0 : biography.hashCode());
        result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((closingDate == null) ? 0 : closingDate.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + (emailVerifed ? 1231 : 1237);
        result = prime * result + ((footer == null) ? 0 : footer.hashCode());
        result = prime * result + id;
        result = prime * result + idBackgroundPicture;
        result = prime * result + idProfilePicture;
        result = prime * result + ((lastLoginDate == null) ? 0 : lastLoginDate.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((registerDate == null) ? 0 : registerDate.hashCode());
        result = prime * result + ((roles == null) ? 0 : roles.hashCode());
        result = prime * result + ((secondEmail == null) ? 0 : secondEmail.hashCode());
        result = prime * result + ((sex == null) ? 0 : sex.hashCode());
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
        if (active != other.active)
            return false;
        if (biography == null) {
            if (other.biography != null)
                return false;
        } else if (!biography.equals(other.biography))
            return false;
        if (birthday == null) {
            if (other.birthday != null)
                return false;
        } else if (!birthday.equals(other.birthday))
            return false;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (closingDate == null) {
            if (other.closingDate != null)
                return false;
        } else if (!closingDate.equals(other.closingDate))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (emailVerifed != other.emailVerifed)
            return false;
        if (footer == null) {
            if (other.footer != null)
                return false;
        } else if (!footer.equals(other.footer))
            return false;
        if (id != other.id)
            return false;
        if (idBackgroundPicture != other.idBackgroundPicture)
            return false;
        if (idProfilePicture != other.idProfilePicture)
            return false;
        if (lastLoginDate == null) {
            if (other.lastLoginDate != null)
                return false;
        } else if (!lastLoginDate.equals(other.lastLoginDate))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        if (registerDate == null) {
            if (other.registerDate != null)
                return false;
        } else if (!registerDate.equals(other.registerDate))
            return false;
        if (roles == null) {
            if (other.roles != null)
                return false;
        } else if (!roles.equals(other.roles))
            return false;
        if (secondEmail == null) {
            if (other.secondEmail != null)
                return false;
        } else if (!secondEmail.equals(other.secondEmail))
            return false;
        if (sex == null) {
            if (other.sex != null)
                return false;
        } else if (!sex.equals(other.sex))
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
        return "User [id=" + id + ", email=" + email + ", secondEmail=" + secondEmail
                + ", username=" + username + ", password=" + password + ", phone=" + phone
                + ", name=" + name + ", lastName=" + lastName + ", idProfilePicture="
                + idProfilePicture + ", idBackgroundPicture=" + idBackgroundPicture + ", sex=" + sex
                + ", birthday=" + birthday + ", city=" + city + ", biography=" + biography
                + ", footer=" + footer + ", registerDate=" + registerDate + ", emailVerifed="
                + emailVerifed + ", lastLoginDate=" + lastLoginDate + ", active=" + active
                + ", closingDate=" + closingDate + ", roles=" + roles + "]";
    }
    
}
