package com.jsu.pharmacyms.dao;

import com.jsu.pharmacyms.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>用户Dao层</p>
 * */
@Mapper
public interface UserDao {
    /**
     * <p>获取用户信息</p>
     * @param username 用户名
     * @return 用户数据
     * */
    @Select("select * from user where username = #{username}")
    public User getUser(String username);

    /**
     * <p>创建新用户</p>
     * @param id 用户ID 在这里用户ID可以是职工ID
     * */
    @Insert("insert into user(id,username,password,type) values(#{id},#{id},'e10adc3949ba59abbe56e057f20f883e',1)")
    void createAccount(int id);
}
