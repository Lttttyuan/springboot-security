package com.example;

import com.example.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootSecurityApplicationTests {

    @Autowired
    SecurityConfig securityConfig;

    @Test
    void contextLoads() {
        securityConfig.passwordEncoder();
    }

}
