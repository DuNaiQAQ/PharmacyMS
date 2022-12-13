package com.jsu.pharmacyms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsu.pharmacyms.domain.*;
import com.jsu.pharmacyms.service.UserService;
import com.jsu.pharmacyms.utils.MD5Utils;
import com.jsu.pharmacyms.utils.RedisUtil;
import com.jsu.pharmacyms.utils.UidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * <p>用户控制器类</p>
 * <p>实现对用户数据的操作</p>
 * */
@Controller
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    protected UserService userService;

    @Resource
    protected RedisUtil redisUtil;

    /**
     * <p>用户登录</p>
     * <p>后端判断并返回用户登录数据以及权限</p>
     * @param user 传输的用户信息
     * @exception JsonProcessingException json数据转换异常
     * @return 服务器响应数据
     * */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody User user) throws JsonProcessingException {
        ResultInfo info=new ResultInfo();
        Permission permission=new Permission();
        ObjectMapper mapper=new ObjectMapper();
        User user_t=null;
        try{
            user_t=userService.getUser(user.getUsername());
        }catch (Exception e){

        }
        if(user_t!=null){
            if(MD5Utils.string2MD5(user.getPassword()).equals(user_t.getPassword())){
                info.setMessage(String.valueOf(user_t.getType()));
                permission.setUsername(user.getUsername());
                //Redis设置用户信息
                if(user_t.getType()==1) {
                    redisUtil.set("login_admin", user_t);
                    info.setMessage("1");
                }else {
                    redisUtil.set("login_user", user_t);
                    info.setMessage("2");
                }
                info.setCode(200);
                info.setRes(permission);
            }else{
                info.setCode(201);
                info.setMessage("密码错误");
            }
        }else {
            info.setCode(201);
            info.setMessage("用户名错误!");
        }
        return mapper.writeValueAsString(info);
    }

    @RequestMapping("/logout_admin")
    @ResponseBody
    public String logout_admin()throws JsonProcessingException{
        User user=null;
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            user= (User) redisUtil.get("login_admin");
        }catch (Exception e){

        }
        if(user!=null){
            redisUtil.del("login_admin");
            info.setCode(200);
            info.setMessage("退出登录成功");
        }else{
            info.setCode(201);
            info.setMessage("用户登录信息失效，请重新登录");
        }
        return mapper.writeValueAsString(info);
    }


    @RequestMapping("/getrole")
    @ResponseBody
    public String getRole()throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        User user_t=null;
        try{
            user_t= (User) redisUtil.get("loginuser");
        }catch (Exception e){

        }
        if(user_t==null){
            info.setCode(201);
            info.setMessage("用户登录信息失效，请重新登录");
        }else{
            info.setCode(200);
            if(user_t.getType()==1){
                info.setMessage("admin");
            }else{
                info.setMessage("user");
            }
        }
        return mapper.writeValueAsString(info);
    }


    @RequestMapping("/getinfo")
    @ResponseBody
    public String getInfo()throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        User user_t=null;
        try{
            user_t= (User) redisUtil.get("loginuser");
        }catch (Exception e){

        }
        if(user_t==null){
            info.setCode(201);
            info.setMessage("用户登录信息失效，请重新登录");
        }else{
            info.setCode(200);
            info.setMessage("获取信息成功");
            info.setRes(user_t);
        }
        return mapper.writeValueAsString(info);
    }

    @RequestMapping(value = "/changePass",method = RequestMethod.POST)
    @ResponseBody
    public String ChangePass(@RequestBody User user) throws JsonProcessingException {
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            userService.changePassword(MD5Utils.string2MD5(user.getPassword()),user.getId());
            info.setCode(200);
            info.setMessage("修改密码成功");
        }catch (Exception e){
            e.printStackTrace();
            info.setMessage("服务器内容错误");
            info.setCode(500);
        }
        return mapper.writeValueAsString(info);
    }

    @RequestMapping(value = "/changePass_admin",method = RequestMethod.POST)
    @ResponseBody
    public String ChangePass_admin(@RequestBody User user) throws JsonProcessingException {
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            userService.changePassword_admin(MD5Utils.string2MD5(user.getPassword()),user.getUsername());
            info.setCode(200);
            info.setMessage("修改密码成功");
        }catch (Exception e){
            e.printStackTrace();
            info.setMessage("服务器内容错误");
            info.setCode(500);
        }
        return mapper.writeValueAsString(info);
    }

    @RequestMapping(value = "/getWarning")
    @ResponseBody
    public String getWarning() throws JsonProcessingException {
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            DataList dataList=new DataList();
            dataList.setItems(redisUtil.lGet("warning",0,-1));
            info.setRes(dataList);
            info.setCode(200);
            info.setMessage("获取数据成功");
        }catch (Exception e){
            e.printStackTrace();
            info.setMessage("服务器内容错误");
            info.setCode(500);
        }
        return mapper.writeValueAsString(info);
    }

    @RequestMapping(value = "/getHome")
    @ResponseBody
    public String getHomeInfo() throws JsonProcessingException {
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            info.setRes(userService.getDashbordInfo());
            info.setCode(200);
            info.setMessage("获取数据成功");
        }catch (Exception e){
            e.printStackTrace();
            info.setMessage("服务器内容错误");
            info.setCode(500);
        }
        return mapper.writeValueAsString(info);
    }
}
