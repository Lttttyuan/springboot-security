package com.example.service.impl;

import com.example.entity.Permission;
import com.example.mapper.PermissionMapper;
import com.example.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-08-17
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<String> getUserPermissionById(Integer id) {
        return permissionMapper.getUserPermissionById(id);
    }
}
