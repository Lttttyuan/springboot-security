package com.example.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@Slf4j
@SpringBootTest
class UserMapperTest {

    @Autowired
    private PermissionMapper permissionMapper;

    @Test
    void getUserPermissionByIdTest() {
        List<String> userPermissions = permissionMapper.getUserPermissionById(3);
        log.info(userPermissions.toString());
    }
}