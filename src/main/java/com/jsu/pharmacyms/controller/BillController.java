package com.jsu.pharmacyms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsu.pharmacyms.domain.*;
import com.jsu.pharmacyms.service.BillService;
import com.jsu.pharmacyms.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bill")
@CrossOrigin
public class BillController {
    @Autowired
    protected BillService billService;

    @Autowired
    protected RedisUtil redisUtil;

    //写两个方法:将订单信息放入缓存中 结算的时候取出缓存信息并清空缓存
    @PostMapping("/additem_admin")
    @ResponseBody
    public String addItem(@RequestBody SoldInfo soldInfo) throws JsonProcessingException {
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        User user=null;
        try{
            soldInfo.setSold_user("药房工作人员");
            redisUtil.lSet("sellitems_admin",soldInfo);//向RedisList中设置缓存
            info.setCode(200);
            info.setMessage("操作执行成功");
        }catch (Exception e) {
            info.setCode(500);
            info.setMessage("错误:" + e.getMessage());
        }
        return mapper.writeValueAsString(info);
    }


    @GetMapping("/setsell_admin")
    @ResponseBody
    public String setsell() throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            List<Object> items=redisUtil.lGet("sellitems_admin",0,-1);//获取所有信息
            //这里用一个非常古老的方法：直接每个每个添加数据
            for(int i=0;i<items.size();i++){
                SoldInfo temp= (SoldInfo) items.get(i);
                temp.setSold_time(new Timestamp(System.currentTimeMillis()));
                billService.addSoldInfo(temp);
                temp.setSold_time(null);
                billService.SoldDrug(temp.getDrug_id(),temp.getSold_num(),temp.getBatch_id());
                billService.addBillInfo("用户购买药品",temp.getSold_sum(),new Timestamp(System.currentTimeMillis()),2);
                redisUtil.lRemove("sellitems_admin",1,temp);
            }
            User user= (User) redisUtil.get("login_admin");
            info.setCode(200);
            info.setMessage("操作执行成功");
        }catch (Exception e) {
            info.setCode(500);
            info.setMessage("错误:" + e.getMessage());
        }
        return mapper.writeValueAsString(info);
    }


    @GetMapping("/getitems_admin")
    @ResponseBody
    public String getItems_admin()throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            List<Object> items=redisUtil.lGet("sellitems_admin",0,-1);//获取所有信息
            double sum=0;
            for(int i=0;i<items.size();i++){
                SoldInfo item=(SoldInfo) items.get(i);
                sum+=item.getSold_sum();
            }
            DataList dataList=new DataList();
            dataList.setItems(items);
            dataList.setItemNums(items.size());
            dataList.setValue_sum(sum);
            info.setRes(dataList);
            info.setCode(200);
            info.setMessage("获取数据成功");
        }catch (Exception e) {
            info.setCode(500);
            info.setMessage("错误:" + e.getMessage());
        }
        return mapper.writeValueAsString(info);
    }


    @GetMapping("/getinfo")
    @ResponseBody
    public String getinfo()throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            DataList dataList=new DataList();
            dataList.setItems(billService.getBillInfo());
            dataList.setItemNums(dataList.getItems().size());
            info.setRes(dataList);
            info.setCode(200);
            info.setMessage("获取数据成功");
        }catch (Exception e) {
            info.setCode(500);
            info.setMessage("错误:" + e.getMessage());
        }
        return mapper.writeValueAsString(info);
    }

    @GetMapping("/getsellinfo")
    @ResponseBody
    public String getsellinfo()throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            DataList dataList=new DataList();
            dataList.setItems(billService.getSellInfo());
            dataList.setItemNums(dataList.getItems().size());
            info.setRes(dataList);
            info.setCode(200);
            info.setMessage("获取数据成功");
        }catch (Exception e) {
            info.setCode(500);
            info.setMessage("错误:" + e.getMessage());
        }
        return mapper.writeValueAsString(info);
    }

    @RequestMapping("/delitem_admin")
    @ResponseBody
    public String delitem_admin(@RequestBody SoldInfo soldInfo) throws JsonProcessingException {
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            redisUtil.lRemove("sellitems_admin",1,soldInfo);
            info.setCode(200);
            info.setMessage("删除数据成功");
        }catch (Exception e) {
            info.setCode(500);
            info.setMessage("错误:" + e.getMessage());
        }
        return mapper.writeValueAsString(info);
    }


    @RequestMapping("/get_five_days")
    @ResponseBody
    public String getFiveDays() throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            CuculateInfo cuculateInfo=new CuculateInfo();
            List<String> label=new ArrayList<>();
            List<Integer> value=new ArrayList<>();
            List<Integer> value2=new ArrayList<>();
            List<Caculate> list=billService.getInfo_date();
            int lens=5;
            if(list.size()<=5){
                lens=list.size();
            }
            for(int i=0;i<lens;i++){
                label.add(list.get(i).getTime());
                value.add(list.get(i).getOut());
                value2.add(list.get(i).getIn());
            }
            cuculateInfo.setLabel(label);
            cuculateInfo.setValues(value2);
            cuculateInfo.setOut_values(value);
            info.setRes(cuculateInfo);
            info.setCode(200);
            info.setMessage("获取数据成功");
        }catch (Exception e){
            info.setCode(500);
            info.setMessage("错误:" + e.getMessage());
        }
        return mapper.writeValueAsString(info);
    }

    @RequestMapping("/get_five_month")
    @ResponseBody
    public String getFiveMonth() throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            CuculateInfo cuculateInfo=new CuculateInfo();
            List<String> label=new ArrayList<>();
            List<Integer> value=new ArrayList<>();
            List<Integer> value2=new ArrayList<>();
            List<Caculate> list=billService.getInfo_month();
            int lens=5;
            if(list.size()<=5){
                lens=list.size();
            }
            for(int i=0;i<lens;i++){
                label.add(list.get(i).getTime());
                value.add(list.get(i).getOut());
                value2.add(list.get(i).getIn());
            }
            cuculateInfo.setLabel(label);
            cuculateInfo.setValues(value2);
            cuculateInfo.setOut_values(value);
            info.setRes(cuculateInfo);
            info.setCode(200);
            info.setMessage("获取数据成功");
        }catch (Exception e){
            info.setCode(500);
            info.setMessage("错误:" + e.getMessage());
        }
        return mapper.writeValueAsString(info);
    }
}
