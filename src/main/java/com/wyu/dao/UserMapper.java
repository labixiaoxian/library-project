package com.wyu.dao;

import com.wyu.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by XiaoXian on 2020/11/17.
 */
public interface UserMapper {



    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    User findUserByUsername(@Param("username")String username);

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 根据用户名删除用户信息
     * @param username
     * @return
     */
    int deleteUserByUsername(@Param("username")String username);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 根据用户名查询角色
     * @param username
     * @return
     */
    User findRolesByUsername(@Param("username")String username);


    User findUserById(@Param("userId")Integer userId);


}
