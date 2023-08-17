package com.example.mapper;

import com.example.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2023-08-17
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("SELECT rpr.permission_path FROM user_role_rbac usr LEFT JOIN role_permission_rbac rpr ON usr.rid = rpr.rid WHERE usr.uid = #{id} GROUP BY rpr.permission_path;")
    List<String> getUserPermissionById(@Param("id") Integer id);
}
