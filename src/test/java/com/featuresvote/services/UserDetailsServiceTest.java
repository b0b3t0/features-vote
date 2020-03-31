package com.featuresvote.services;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertNotEquals;

public class UserDetailsServiceTest {

    @Test
    public void generate_encrypted_password() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "password123";
        String encodedPassword = encoder.encode("password123");

        assertNotEquals(rawPassword,encodedPassword);
    }

}