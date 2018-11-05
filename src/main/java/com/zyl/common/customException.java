package com.zyl.common;

/** 自定义 异常类
 * Created by z1761 on 2018/10/10.
 */
public class customException extends Exception {
    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public customException(customEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMag();
    }

}
