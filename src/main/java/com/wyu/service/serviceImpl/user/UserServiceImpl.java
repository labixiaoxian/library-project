package com.wyu.service.serviceImpl.user;

import com.wyu.dao.UserMapper;
import com.wyu.entity.User;
import com.wyu.service.user.UserService;
import com.wyu.utils.SaltUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * Created by XiaoXian on 2020/11/18.
 */
@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUsername(String username) {
        if (!StringUtils.isEmpty(username)) {
            return userMapper.findUserByUsername(username);
        }
        return null;
    }

    @Override
    public User findRolesByUsername(String username) {
        if (!StringUtils.isEmpty(username)) {
            return userMapper.findRolesByUsername(username);
        }
        return null;
    }

    @Override
    public int modifyPassword(Integer userId, String oldPassword, String newPassword) {
        //数据为空
        if (ObjectUtils.isEmpty(userId) || ObjectUtils.isEmpty(oldPassword) || ObjectUtils.isEmpty(newPassword)) {
            return 0;
        }
        User userById = userMapper.findUserById(userId);
        //查询不到该用户
        if (ObjectUtils.isEmpty(userById)) {
            return -1;
        }

        //新密码与旧密码相同
        if (oldPassword.equals(newPassword)) {
            return -2;
        }

        //旧密码不正确
        Md5Hash old = new Md5Hash(oldPassword, userById.getPwdSalt(), 1024);
        if (!old.toHex().equals(userById.getPwdHash())) {
            return -3;
        }


        //对用户的密码进行加密
        String salt = SaltUtil.getSalt(8);
        Md5Hash md5Hash = new Md5Hash(newPassword, salt, 1024);
        //对用户的密码和盐进行更改
        User user = new User();
        user.setId(userId);
        user.setPwdHash(md5Hash.toHex());
        user.setPwdSalt(salt);
        return userMapper.updateUser(user);

    }
}
