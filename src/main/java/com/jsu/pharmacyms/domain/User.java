package com.jsu.pharmacyms.domain;

/**
 * 基本用户类
 * */
public class User {
    private int id;//用户ID
    private String username;//用户名
    private String password;//用户密码
    private int type;//用户类型 1:管理员 2:买

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
