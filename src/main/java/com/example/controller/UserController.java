package com.example.controller;


import com.example.entity.User;
import com.example.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2023-08-16
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口", description = "提供用户相关的 Rest API")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @ApiOperation("新增用户接口")
    @PostMapping("/add")
    public boolean addUser(@ApiParam("用户实体") @RequestBody User user) {
        return false;
    }

    @ApiOperation("根据id查询用户信息")
    @GetMapping("/find/{id}")
    public User findById(@PathVariable("id") int id) {
        return userMapper.selectById(id);
    }

    @ApiOperation("修改用户信息")
    @PutMapping("/update")
    public boolean update(@RequestBody User user) {
        return true;
    }

    @ApiOperation("根据id删除用户")
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") int id) {
        return true;
    }

}
