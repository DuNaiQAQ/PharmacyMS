package com.jsu.pharmacyms.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 服务器返回数据
 * */
public class ResultInfo<T> implements Serializable {
    private int code;//返回码
    private String message;//返回消息
    private T res;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getRes() {
        return res;
    }

    public void setRes(T res) {
        this.res = res;
    }
}
