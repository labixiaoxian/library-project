package com.wyu.entity;

import java.io.Serializable;

/**
 * Created by XiaoXian on 2020/11/19.
 */
public class UserRole implements Serializable {


    private static final long serialVersionUID = 5516735687106075501L;
    private Integer id;

    private Integer userId;

    private Integer roleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
