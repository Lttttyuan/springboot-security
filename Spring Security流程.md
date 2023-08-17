### Spring Security流程

##### 第一步:判断登录的用户是否存在SecurityUserDetailsService

```java
/**
 * 第一步:判断登录的用户是否存在
 */
@Service
public class SecurityUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User userInfo = userMapper.selectOne(queryWrapper);

        if (userInfo == null){
            throw new UsernameNotFoundException("该用户不存在");
        }

        //查询用户资源
        List<String> userPermissions = 	permissionService
            .getUserPermissionById(userInfo.getId());

        List<SimpleGrantedAuthority> authorityList = userPermissions
            .stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
        //判断用户存在后将userInfo传入SecurityUser
        SecurityUser securityUser = new SecurityUser(userInfo);

        //设置用户的资源
        securityUser.setAuthorityList(authorityList);
        return securityUser;
    }
}
```

##### 第二步:判读用户的各种权限信息SecurityUser

```java
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
```

##### 第三步：加载SecurityConfig配置文件

```java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin().permitAll();
    }
}
```

##### 第四步：获取认证信息CurrentLoginUserController

```java
/**
 * 获取认证信息的三种方式
 */
@RestController
public class CurrentLoginUserController {
    @GetMapping("/getLoginUser1")
    public Authentication getLoginUser1(Authentication authentication){
        return authentication;
    }

    @GetMapping("/getLoginUser2")
    public Principal getLoginUser2(Principal principal){
        return principal;
    }

    @GetMapping("/getLoginUser3")
    public Principal getLoginUser3(){
        //通过安全上下文持有器获取安全上下文，再获取认证信息
        return SecurityContextHolder.getContext().getAuthentication();

    }
}
```