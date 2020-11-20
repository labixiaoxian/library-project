package com.wyu.service.user;

import com.wyu.entity.User;
import com.wyu.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

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


    /**
     * 用户注册
     * @param userName
     * @param nickname
     * @param email
     * @param password
     * @param twicePassword
     * @return
     */
    int register(String userName,String nickname,String email,String password,String twicePassword);

    /**
     * 激活用户
     * @param userId
     * @return
     */
    int activeUser(Integer userId);


    /**
     * 注销用户
     * @param userId
     * @return
     */
    int cancellation(Integer userId);




}
