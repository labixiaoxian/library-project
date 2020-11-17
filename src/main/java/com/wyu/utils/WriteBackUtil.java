package com.wyu.utils;

import com.wyu.enums.Constant;
import com.wyu.vo.WriteBack;

public class WriteBackUtil {
    public static void setFail(WriteBack writeBack) {
        writeBack.setCode(Constant.fail.getCode());
        writeBack.setMsg(Constant.fail.getMsg());
    }

    public static void setSuccess(WriteBack writeBack) {
        writeBack.setCode(Constant.success.getCode());
        writeBack.setMsg(Constant.success.getMsg());
    }
}
