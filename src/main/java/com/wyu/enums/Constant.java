package com.wyu.enums;

public enum Constant {
    success(200, "操作成功"), fail(500, "操作失败");

    private int code;
    private String msg;

    private Constant(int i, String string) {
        // TODO Auto-generated constructor stub
        this.code = i;
        this.msg = string;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }
}
