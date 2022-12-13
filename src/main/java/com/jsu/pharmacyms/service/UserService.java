package com.jsu.pharmacyms.service;

import com.jsu.pharmacyms.domain.User;

public interface UserService {
    public User getUser(String username);
    public void createAccount(int id);
    void changePassword(String password,int id);
    void changePassword_admin(String password,String name);
    void addNewAccount_NoAdmin(String id,String username);
    DashBoardInfo getDashbordInfo();
}
