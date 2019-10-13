package com.example.a11699.mydemo.base;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：余智强
 * 2019/9/18
 */
public class BaseBean<T> implements Serializable{

    /**
     * code : 100
     * msg : 处理成功
     * extend : {}
     */

    private int code;
    private String msg;
    private T extend;

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

    public T getExtend() {
        return extend;
    }

    public void setExtend(T extend) {
        this.extend = extend;
    }
}

