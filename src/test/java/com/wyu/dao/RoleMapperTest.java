package com.wyu.dao;

import com.wyu.entity.Role;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by XiaoXian on 2020/11/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleMapperTest {

    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void insertRole() throws Exception {
        Role role = new Role();
        role.setRoleName("user");
        int result = roleMapper.insertRole(role);
        Assert.assertEquals(1,result);
    }

}