package com.jsu.pharmacyms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsu.pharmacyms.domain.Permission;
import com.jsu.pharmacyms.domain.ResultInfo;
import com.jsu.pharmacyms.domain.User;
import com.jsu.pharmacyms.service.UserService;
import com.jsu.pharmacyms.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        User user_t=userService.getUser(user.getUsername());
        if(user_t!=null){
            if(MD5Utils.string2MD5(user.getPassword()).equals(user_t.getPassword())){
                info.setCode(200);
                info.setMessage("登陆成功");
                permission.setUsername(user.getUsername());
                if(user_t.getType()==1){
                    permission.setAdmin(true);
                }else {
                    permission.setAdmin(false);
                }
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


}
