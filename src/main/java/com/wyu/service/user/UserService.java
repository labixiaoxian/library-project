package com.wyu.service.user;

import com.wyu.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by XiaoXian on 2020/11/18.
 */
public interface UserService {

    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 根据用户名查询角色
     * @param username
     * @return
     */
    User findRolesByUsername(String username);

    /**
     * 修改密码
     * @param userId
     * @param oldPassword newPassword
     * @return
     */
    int modifyPassword(Integer userId, String oldPassword,String newPassword);


}
