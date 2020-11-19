package com.wyu.enums;

/**
 * Created by XiaoXian on 2020/11/18.
 */
public enum  UserStatusEnums {
    NULL_PARAMETER(105,"参数为空"),
    NO_USER(106,"查询不到该用户"),
    OLD_EQUAL_NEW(107,"新密码与旧密码相同"),
    ERROR_PASSWORD(108,"密码错误")
    ;

    private int state;

    private String stateInfo;

    UserStatusEnums(int state, String stateInfo) {
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
    public static UserStatusEnums stateof(int state) {
        for(UserStatusEnums stateEnum:values()) {
            if(stateEnum.getState() == state) {
                return stateEnum;
            }
        }
        return null;
    }
}
