package com.jsu.pharmacyms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsu.pharmacyms.domain.*;
import com.jsu.pharmacyms.service.FactoryService;
import com.jsu.pharmacyms.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;

@Controller
@RequestMapping("/factory")
@CrossOrigin
public class FactoryController {
    @Autowired
    protected FactoryService factoryService;

    @Autowired
    protected RedisUtil redisUtil;

    @RequestMapping("/addfac")
    @ResponseBody
    public String addNewFac(@RequestBody Factory factory)throws JsonProcessingException {
        ResultInfo inf=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            factoryService.addFactoryInfo(factory);
            inf.setCode(200);
            inf.setMessage("添加信息成功");
        }catch (Exception e){
            inf.setMessage("错误:"+e.getMessage());
            inf.setCode(500);
        }
        User user= (User) redisUtil.get("login_admin");
        return mapper.writeValueAsString(inf);
    }

    @RequestMapping("/delfac")
    @ResponseBody
    public String delfac(@RequestBody int id)throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            factoryService.deleteFactoryInfo(id);
            info.setCode(200);
            info.setMessage("删除信息成功");
        }catch (Exception e){
            info.setMessage("错误:"+e.getMessage());
            info.setCode(500);
        }
        User user= (User) redisUtil.get("login_admin");
        return mapper.writeValueAsString(info);
    }

    @RequestMapping("/updatefac")
    @ResponseBody
    public String updatefacInfo(@RequestBody Factory factory)throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            factoryService.updateFactoryInfo(factory);
            info.setCode(200);
            info.setMessage("修改信息成功");
        }catch (Exception e){
            info.setMessage("错误:"+e.getMessage());
            info.setCode(500);
        }
        User user= (User) redisUtil.get("login_admin");
        return mapper.writeValueAsString(info);
    }

    @RequestMapping("/getfac")
    @ResponseBody
    public String getfacInfos()throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            DataList dataList=new DataList();
            dataList.setItems(factoryService.getFactoryInfos());
            dataList.setItemNums(dataList.getItems().size());
            info.setRes(dataList);
            info.setCode(200);
            info.setMessage("获取信息成功");
        }catch (Exception e){
            info.setMessage("错误:"+e.getMessage());
            info.setCode(500);
        }
        return mapper.writeValueAsString(info);
    }
}
