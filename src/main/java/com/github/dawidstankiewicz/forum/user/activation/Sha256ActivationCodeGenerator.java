package com.github.dawidstankiewicz.forum.user.activation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Component;

@Component
public class Sha256ActivationCodeGenerator implements ActivationCodeGenerator {

    @Override
    public String generate() {
        return generateActivationCode();
    }

    private String generateActivationCode() {
        return (sha256("" + Math.random()));
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
