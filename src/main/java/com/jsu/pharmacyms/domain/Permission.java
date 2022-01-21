package com.jsu.pharmacyms.domain;

/**
 * 服务器返回用户权限以及用户名
 * */
public class Permission {
    private String username;//用户名
    private boolean isAdmin;//是否为管理员

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
