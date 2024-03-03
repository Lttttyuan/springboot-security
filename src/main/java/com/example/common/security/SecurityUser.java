package com.example.common.security;

import com.example.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 第二步:判读用户的各种权限信息
 */
public class SecurityUser implements UserDetails {
    private final User user;

    //用于存储用户资源的list
    private List<SimpleGrantedAuthority> authorityList;

    public SecurityUser(User user) {
        this.user = user;
    }

    /**
     * 返回用户所拥有的的权限（资源）
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorityList;
    }

    /**
     * 设置资源
     * @param authorityList
     */
    public void setAuthorityList(List<SimpleGrantedAuthority> authorityList) {
        this.authorityList = authorityList;
    }

    /**
     * 判断输入的密码是否与数据库加密后的密码是否相同
     * @return
     */
    @Override
    public String getPassword() {
        String myPassword=this.user.getPassword();
        this.user.setPassword(null); //擦除我们的密码，防止传到前端
        return myPassword;
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
