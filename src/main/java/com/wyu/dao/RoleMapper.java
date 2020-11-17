package com.wyu.dao;

import com.wyu.entity.Role;

/**
 * Created by XiaoXian on 2020/11/18.
 */
public interface RoleMapper {

    /**
     * 添加用户角色信息
     * @param role
     * @return
     */
    int insertRole(Role role);
}
