package com.zyl.common;

import java.io.Serializable;

/**
 * Created by z1761 on 2018/10/10.
 */
//自定义返回体
public class customResult<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public customResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
