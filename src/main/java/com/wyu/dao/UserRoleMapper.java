package com.wyu.dao;

import com.wyu.entity.UserRole;
import org.apache.ibatis.annotations.Param;

/**
 * Created by XiaoXian on 2020/11/19.
 */
public interface UserRoleMapper {

    int insertUserRole(UserRole userRole);
}
