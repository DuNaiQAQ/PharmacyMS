package com.jsu.pharmacyms.service.Impl;

import com.jsu.pharmacyms.dao.UserDao;
import com.jsu.pharmacyms.domain.User;
import com.jsu.pharmacyms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    protected UserDao userDao;

    /**
     * <p>获取用户数据</p>
     * @param username 用户名
     * @return 用户数据
     * */
    @Override
    public User getUser(String username) {
        return userDao.getUser(username);
    }

    /**
     * <p>创建账户</p>
     * @param id 用户ID
     * */
    @Override
    public void createAccount(int id) {
        userDao.createAccount(id);
    }

    @Override
    public void changePassword(String password, int id) {
        userDao.changePassword(id,password);
    }

    @Override
    public void changePassword_admin(String password, String name) {
        userDao.changePassword_admin(name,password);
    }

    @Override
    public void addNewAccount_NoAdmin(String id,String username) {
        userDao.createAccount_NoAdmin(id,username);
    }

    @Override
    public DashBoardInfo getDashbordInfo() {
        return new DashBoardInfo(userDao.getHomeInfo_drug(), userDao.getHomeInfo_user());
    }

}
