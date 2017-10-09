package com.github.dawidstankiewicz.forum.entity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Dawid Stankiewicz on 10.08.2017.
 */

@Entity
public class ActivationCode {

    @Id
    private String id;

    @Column(unique = true, nullable = false, updatable = false)
    private String username;

    @Column(nullable = false)
    private Date timestamp;

    public ActivationCode() {
    }

    public ActivationCode(String username) {
        this.username = username;

        this.id = generateActivationCode(username);
        this.timestamp = new Date();
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    private String generateActivationCode(String username) {
        return (sha256(username + Math.random()));
    }

    private String sha256(String input) {
        MessageDigest mDigest = null;
        try {
            mDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
