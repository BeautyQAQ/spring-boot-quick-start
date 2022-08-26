package com.quick.start.securityframework;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmokeTest {

    @Test
    void one_plus_one_should_equals_two() {
        assertEquals(2, 1 + 1);
    }

    @Test
    void should_print_password() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("admin"));
    }
}
