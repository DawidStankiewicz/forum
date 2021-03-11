package com.github.dawidstankiewicz.forum.user.activation;

import com.github.dawidstankiewicz.forum.user.User;
import java.util.Date;
import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "activation_code")
@Data
@NoArgsConstructor
public class ActivationCode {

    @Id
    private String id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user")
    private User user;

    @Column(nullable = false)
    private Date timestamp;

    public ActivationCode(String id) {
        this.id = id;
        this.timestamp = new Date();
    }

}
