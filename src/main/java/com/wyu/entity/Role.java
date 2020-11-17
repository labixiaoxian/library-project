package com.wyu.entity;

import java.io.Serializable;

/**
 * Created by XiaoXian on 2020/11/17.
 */
public class Role implements Serializable {


    private static final long serialVersionUID = -3744002248357058390L;

    private Integer id;

    private String roleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
