package com.hillel.app.API;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Custom {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        System.out.println(encoder.encode("testpassword"));
    }
}
