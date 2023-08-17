package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.User;
import com.example.vo.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 第一步:判断登录的用户是否存在
 */
@Service
public class SecurityUserDetailsService implements UserDetailsService {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PermissionServiceImpl permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User userInfo = userService.getOne(queryWrapper);

        if (userInfo == null){
            throw new UsernameNotFoundException("该用户不存在");
        }
        //查询用户资源
        List<String> userPermissions = permissionService.getUserPermissionById(userInfo.getId());

        List<SimpleGrantedAuthority> authorityList = userPermissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        //判断用户存在后将userInfo传入SecurityUser
        SecurityUser securityUser = new SecurityUser(userInfo);

        //设置用户的资源
        securityUser.setAuthorityList(authorityList);

        return securityUser;
    }
}
