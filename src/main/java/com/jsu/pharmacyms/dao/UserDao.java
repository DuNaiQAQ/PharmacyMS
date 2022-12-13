package com.jsu.pharmacyms.dao;

import com.jsu.pharmacyms.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    @Insert("insert into user(id,username,password,type) values(#{id},#{username},'e10adc3949ba59abbe56e057f20f883e',2)")
    void createAccount_NoAdmin(String id,String username);

    @Update("update user set password = #{password} where id = #{id}")
    void changePassword(int id,String password);

    @Update("update user set password = #{password} where username = #{username}")
    void changePassword_admin(String username,String password);

    //以下获取所有默认数据
    @Select("select count(*) as 'drug_num' from drug_info")
    int getHomeInfo_drug();

    @Select("select count(*) as 'user_num' from user")
    int getHomeInfo_user();
}
