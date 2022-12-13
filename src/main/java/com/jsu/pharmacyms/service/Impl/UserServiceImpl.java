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

}
