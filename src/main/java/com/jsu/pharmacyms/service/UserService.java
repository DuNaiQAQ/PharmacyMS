package com.jsu.pharmacyms.service;

import com.jsu.pharmacyms.domain.DashBoardInfo;
import com.jsu.pharmacyms.domain.User;

import java.util.List;

public interface UserService {
    public User getUser(String username);
    public void createAccount(int id);
    void changePassword(String password,int id);
    void changePassword_admin(String password,String name);
    void addNewAccount_NoAdmin(String id,String username);
    DashBoardInfo getDashbordInfo();
}
