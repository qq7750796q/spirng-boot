package com.zyl.common;

/**
 * Created by z1761 on 2018/10/10.
 */
//自定义枚举类
public enum customEnum {
    UNKNOW(-1, "未知错误"),
    OKKK(100, "小于18岁"),
    NOOO(101, "大于50岁"),;

    private Integer code;
    private String msg;

    customEnum(Integer code, String mag) {
        this.code = code;
        this.msg = mag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMag() {
        return msg;
    }

    public void setMag(String mag) {
        this.msg = mag;
    }

}
