package com.wyu.enums;

/**
 * Created by XiaoXian on 2020/11/18.
 */
public enum LoginStatusEnums {
    LOGIN_SUCCESS(200, "登录成功"),
    PASSWORD_ERROR(101, "密码错误"),
    NO_AUTH(102, "没有权限"),
    USER_LOGOUT(103,"用户已注销"),
    NO_USER(104,"用户不存在"),

    ;

    private int state;

    private String stateInfo;

    LoginStatusEnums(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    /**
     * 依据传入的state返回相应的enum值
     * @param state
     * @return
     */
    public static LoginStatusEnums stateof(int state) {
        for(LoginStatusEnums stateEnum:values()) {
            if(stateEnum.getState() == state) {
                return stateEnum;
            }
        }
        return null;
    }
}
