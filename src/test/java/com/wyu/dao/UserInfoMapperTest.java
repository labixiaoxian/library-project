package com.wyu.dao;

import com.wyu.entity.User;
import com.wyu.entity.UserInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by XiaoXian on 2020/11/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoMapperTest {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void findUserInfoByUserId() throws Exception {
        UserInfo userInfoByUserId = userInfoMapper.findUserInfoByUserId(1);
        Assert.assertEquals("xiaoxian",userInfoByUserId.getUser().getUsername());
    }

    @Test
    public void findUserInfoById() throws Exception {
        UserInfo userInfoById = userInfoMapper.findUserInfoById(1);
        Assert.assertEquals("xiaoxian",userInfoById.getUser().getUsername());
    }

    @Test
    public void insertUserInfo() throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setAddress("五邑大学");
        userInfo.setAge(22);
        userInfo.setBirthday(new Date());
        userInfo.setEmail("4548648f");
        userInfo.setNickname("小贤");
        userInfo.setPersonalDesc("真帅");
        userInfo.setPicture("xxxx.jpg");
        userInfo.setSex(1);
        userInfo.setTelephone("123456789");
        User user = new User();
        user.setId(1);
        userInfo.setUser(user);
        int result = userInfoMapper.insertUserInfo(userInfo);
        Assert.assertEquals(1,result);
    }

    @Test
    public void updateUserInfo() throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setBirthday(new Date());
        userInfo.setEmail("869160919@qq.com");
        userInfo.setId(1);
        int result = userInfoMapper.updateUserInfo(userInfo);
        Assert.assertEquals(1,result);
    }

    @Test
    public void deleteUserInfoById() throws Exception {
        int result = userInfoMapper.deleteUserInfoById(1);
        Assert.assertEquals(1,result);
    }

    @Test
    public void queryUserInfo() throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setNickname("小");
        List<UserInfo> userInfos = userInfoMapper.queryUserInfo(userInfo, 0, 10);
        Assert.assertEquals(1,userInfos.size());
        User user = new User();
        user.setCredit(100);
        userInfo.setUser(user);
        List<UserInfo> userInfos1 = userInfoMapper.queryUserInfo(userInfo, 0, 10);
        Assert.assertEquals(1,userInfos1.size());
    }

    @Test
    public void queryUserInfoCount() throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setNickname("小");
        int result = userInfoMapper.queryUserInfoCount(userInfo);
        Assert.assertEquals(1,result);
    }

}