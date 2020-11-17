package com.wyu.dao;

import com.wyu.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by XiaoXian on 2020/11/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void queryUserList() throws Exception {
    }

    @Test
    public void findUserByUsername() throws Exception {
        User user = userMapper.findUserByUsername("xiaoxian");
        Assert.assertEquals("xiaoxian",user.getUsername());
    }

    @Test
    public void insertUser() throws Exception {
        User user = new User();
        user.setUsername("xiaoxian");
        user.setPwdHash("123456");
        user.setPwdSalt("123456");
        user.setBorrowCount(10);
        user.setRegisterTime(new Date());
        user.setCredit(100);
        int result = userMapper.insertUser(user);
        Assert.assertEquals(result,1);
    }

    @Test
    public void deleteUserByUsername() throws Exception {
        int result = userMapper.deleteUserByUsername("xiaoxian");
        Assert.assertEquals(1,result);
    }

    @Test
    public void updateUser() throws Exception {
        User user = new User();
        user.setPwdSalt("afafe");
        user.setId(1);
        int result = userMapper.updateUser(user);
        Assert.assertEquals(1,result);
    }

    @Test
    public void findRolesByUsername() throws Exception {
        User user = userMapper.findRolesByUsername("xiaoxian");
        System.out.println(user.getUsername());
        System.out.println(user.getRoles().get(0).getRoleName());
        Assert.assertEquals("admin",user.getRoles().get(0).getRoleName());
    }

}