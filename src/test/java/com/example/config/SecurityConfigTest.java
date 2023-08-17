package com.example.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class SecurityConfigTest {

    @Autowired
    private SecurityConfig securityConfig;
    @Test
    void passwordEncoder() {
        String encode = securityConfig.passwordEncoder().encode("111");
        log.info(encode);
    }
}