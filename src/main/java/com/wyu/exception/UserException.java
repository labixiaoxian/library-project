package com.wyu.exception;

/**
 * Created by XiaoXian on 2020/11/18.
 */
public class UserException extends RuntimeException {
    private static final long serialVersionUID = 2826115879577978756L;

    public UserException(String msg) {
        super(msg);
    }
}
