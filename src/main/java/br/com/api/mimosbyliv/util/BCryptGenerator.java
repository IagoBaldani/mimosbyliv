package br.com.api.mimosbyliv.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptGenerator {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("admin"));
    }
}
