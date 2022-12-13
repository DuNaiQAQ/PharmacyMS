package com.jsu.pharmacyms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsu.pharmacyms.domain.*;
import com.jsu.pharmacyms.service.StaffService;
import com.jsu.pharmacyms.service.UserService;
import com.jsu.pharmacyms.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@Controller
@RequestMapping("/staff")
@CrossOrigin
public class StaffController {

    @Autowired
    protected StaffService staffService;

    @Autowired
    protected UserService userService;

    @Autowired
    protected RedisUtil redisUtil;
    /**
     * <p>增加员工，并且同时创造其对应的账号信息</p>
     * @param staff 上传的员工信息
     * @return 服务器响应数据
     * */
    @RequestMapping(value = "/addStaff",method = RequestMethod.POST)
    @ResponseBody
    public String addStaff(@RequestBody Staff staff) throws JsonProcessingException {
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        Staff t=staffService.getStaff(staff.getId());
        if(t==null){
            staffService.addStaff(staff);
            userService.createAccount(staff.getId());
            info.setMessage("添加成功");
            info.setCode(200);
        }else {
            info.setMessage("添加失败:已有相同编号的员工");
            info.setCode(201);
        }
        User user= (User) redisUtil.get("login_admin");
        return mapper.writeValueAsString(info);
    }

    /**
     * <p>获取职工职称信息</p>
     * @return 服务器响应数据
     * */
    @RequestMapping(value = "/getTypeList")
    @ResponseBody
    public String getTypeList() throws JsonProcessingException {
        ResultInfo info =new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            DataList dataList=new DataList();
            dataList.setItems(staffService.getTypeList());
            dataList.setItemNums(dataList.getItems().size());
            info.setRes(dataList);
            info.setCode(200);
            info.setMessage("获取数据成功");
        }catch (Exception e){
            e.printStackTrace();
            info.setCode(500);
            info.setMessage("获取数据失败");
        }
        User user= (User) redisUtil.get("login_admin");
        return mapper.writeValueAsString(info);
    }

    /**
     * <p>获取所有职工信息</p>
     * @return 服务器响应数据
     * */
    @RequestMapping(value = "/getStaffList")
    @ResponseBody
    public String getStaffInfo() throws JsonProcessingException {
        ResultInfo info =new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            DataList dataList=new DataList();
            dataList.setItems(staffService.getStaffList());
            dataList.setItemNums(dataList.getItems().size());
            info.setRes(dataList);
            info.setCode(200);
            info.setMessage("获取数据成功");
        }catch (Exception e){
            e.printStackTrace();
            info.setCode(500);
            info.setMessage("获取数据失败");
        }
        return mapper.writeValueAsString(info);
    }

    /**
     * <p>通过职工ID删除某个员工的信息</p>
     * @param id 员工ID
     * @return 服务器响应信息
     * */
    @RequestMapping(value = "/deletestaff")
    @ResponseBody
    public String deleteStaff(@RequestBody int id) throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            Staff t=staffService.getStaff(id);
            if(t!=null){
                staffService.deleteStaff(id);
                info.setCode(200);
                info.setMessage("删除成功");
            }else {
                info.setMessage("没有该职工信息!");
                info.setCode(201);
            }
        }catch (Exception e){
            e.printStackTrace();
            info.setMessage("服务器内容错误");
            info.setCode(500);
        }
        User user= (User) redisUtil.get("login_admin");
        return mapper.writeValueAsString(info);
    }

    /**
     * <p>通过职称ID删除职称信息</p>
     * @param id 职称ID
     * @return 服务器响应信息
     * */
    @RequestMapping(value = "/deleteType")
    @ResponseBody
    public String deleteType(@RequestBody int id) throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            StaffType t=staffService.getTypeInfo(id);
            if(t!=null){
                staffService.deleteType(id);
                info.setCode(200);
                info.setMessage("删除成功");
            }else {
                info.setMessage("没有该职称信息!");
                info.setCode(201);
            }
        }catch (Exception e){
            e.printStackTrace();
            info.setMessage("服务器内容错误");
            info.setCode(500);
        }
        User user= (User) redisUtil.get("login_admin");
        return mapper.writeValueAsString(info);
    }

    @RequestMapping(value = "/addType")
    @ResponseBody
    public String addType(@RequestBody StaffType type) throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
                staffService.addStaffType(type);
                info.setCode(200);
                info.setMessage("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            info.setMessage("服务器内容错误");
            info.setCode(500);
        }
        User user= (User) redisUtil.get("login_admin");
        return mapper.writeValueAsString(info);
    }

    @RequestMapping(value = "/updateType")
    @ResponseBody
    public String updateType(@RequestBody StaffType type) throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            staffService.updateStaffType(type);
            info.setCode(200);
            info.setMessage("更新数据成功");
        }catch (Exception e){
            e.printStackTrace();
            info.setMessage("服务器内容错误");
            info.setCode(500);
        }
        User user= (User) redisUtil.get("login_admin");
        return mapper.writeValueAsString(info);
    }

    @RequestMapping("/changeInfo")
    @ResponseBody
    public String changeInfo(@RequestBody Staff info)throws JsonProcessingException{
        ResultInfo resultInfo=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            staffService.updateStaff(info);
            resultInfo.setCode(200);
            resultInfo.setMessage("修改信息成功");
        }catch (Exception e){
            e.printStackTrace();
            resultInfo.setMessage("服务器内容错误");
            resultInfo.setCode(500);
        }
        User user= (User) redisUtil.get("login_admin");
        return mapper.writeValueAsString(resultInfo);
    }
}
