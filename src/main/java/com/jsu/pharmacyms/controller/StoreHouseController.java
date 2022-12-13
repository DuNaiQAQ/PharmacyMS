package com.jsu.pharmacyms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsu.pharmacyms.domain.*;
import com.jsu.pharmacyms.service.BillService;
import com.jsu.pharmacyms.service.DrugService;
import com.jsu.pharmacyms.service.StoreHouseService;
import com.jsu.pharmacyms.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;

/**
 * 仓库管理控制器
 * */
@Controller
@RequestMapping("/sh")
@CrossOrigin
public class StoreHouseController {
    @Autowired
    protected DrugService drugService;

    @Autowired
    protected StoreHouseService storeHouseService;

    @Autowired
    protected BillService billService;

    @Autowired
    protected RedisUtil redisUtil;

    /**
     * <p>购入药品，输入批次信息，并且给总药品信息进行操作</p>
     * @param drugsPurchase 药品购入信息
     * @return 服务器响应数据
     * */
    @RequestMapping("/purchase")
    @ResponseBody
    public String purchase(@RequestBody DrugsPurchase drugsPurchase) throws JsonProcessingException {
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try {
            drugsPurchase.setPurchase_value_sum(drugsPurchase.getPurchase_value()*drugsPurchase.getPurchase_num());
            storeHouseService.purchaseDrug(drugsPurchase);
            drugService.addNum(drugsPurchase.getId(),drugsPurchase.getPurchase_num());
            billService.addBillInfo("药品入库",drugsPurchase.getPurchase_value_sum(), drugsPurchase.getPurchase_time(),1);
            info.setCode(200);
            info.setMessage("入库成功");
        }catch (Exception e){
            e.printStackTrace();
            info.setCode(500);
            info.setMessage("错误:"+e.getMessage());
        }
        User user= (User) redisUtil.get("login_admin");
        return mapper.writeValueAsString(info);
    }


    /**
     * <p>删除入库信息</p>
     * @param id 药品批次ID
     * @return 服务器响应数据
     * */
    @RequestMapping("/purchase_del")
    @ResponseBody
    public String del_purchase(@RequestBody int id) throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            storeHouseService.del_purchase_info(id);
            info.setCode(200);
            info.setMessage("删除信息成功");
        }catch (Exception e){
            info.setCode(500);
            info.setMessage("错误:"+e.getMessage());
        }
        User user= (User) redisUtil.get("login_admin");
        return mapper.writeValueAsString(info);
    }

    @RequestMapping("/purchase_change")
    @ResponseBody
    public String change_purchase(@RequestBody DrugsPurchase drugsPurchase) throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            storeHouseService.updatePurchaseInfo(drugsPurchase);
            info.setCode(200);
            info.setMessage("更新记录成功");
        }catch (Exception e){
            info.setCode(500);
            info.setMessage("错误:"+e.getMessage());
        }
        User user= (User) redisUtil.get("login_admin");
        return mapper.writeValueAsString(info);
    }

    @RequestMapping("/out_add")
    @ResponseBody
    public String sh_out(@RequestBody DrugOut out_info) throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try {
            out_info.setOut_time(new Timestamp(System.currentTimeMillis()));
            out_info.setOut_sum(out_info.getValue()*out_info.getOut_num());
            storeHouseService.addOutInfo(out_info);
            billService.SoldDrug(out_info.getDrug_id(),out_info.getOut_num(),out_info.getBatch_id());
            billService.addBillInfo("药品出库",out_info.getOut_sum(), out_info.getOut_time(),2);
            info.setCode(200);
            info.setMessage("添加记录成功");
        }catch (Exception e){
            info.setCode(500);
            info.setMessage("错误:"+e.getMessage());
        }
        User user= (User) redisUtil.get("login_admin");
        return mapper.writeValueAsString(info);
    }

    @RequestMapping("/out_del")
    @ResponseBody
    public String sh_out_del(@RequestBody int id) throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try {
            storeHouseService.del_out_info(id);
            info.setCode(200);
            info.setMessage("删除记录成功");
        }catch (Exception e){
            info.setCode(500);
            info.setMessage("错误:"+e.getMessage());
        }
        User user= (User) redisUtil.get("login_admin");
        return mapper.writeValueAsString(info);
    }

    @RequestMapping("/get_pur")
    @ResponseBody
    public String get_pur_info() throws JsonProcessingException {
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        DataList list=new DataList();
        try{
            list.setItems(storeHouseService.getPurchase());
            list.setItemNums(list.getItems().size());
            info.setRes(list);
            info.setCode(200);
            info.setMessage("获取记录成功");
        }catch (Exception e){
            info.setCode(500);
            info.setMessage("错误:"+e.getMessage());
        }
        return mapper.writeValueAsString(info);
    }

    @RequestMapping("/get_out")
    @ResponseBody
    public String get_out_info() throws JsonProcessingException {
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        DataList list=new DataList();
        try{
            list.setItems(storeHouseService.getOut());
            list.setItemNums(list.getItems().size());
            info.setRes(list);
            info.setCode(200);
            info.setMessage("获取记录成功");
        }catch (Exception e){
            info.setCode(500);
            info.setMessage("错误:"+e.getMessage());
        }
        return mapper.writeValueAsString(info);
    }

    @RequestMapping("/changeout")
    @ResponseBody
    public String changeOutInfo(@RequestBody DrugOut drugOut) throws JsonProcessingException {
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            storeHouseService.updateOutInfo(drugOut);
            info.setCode(200);
            info.setMessage("更新记录成功");
        }catch (Exception e){
            info.setCode(500);
            info.setMessage("错误:"+e.getMessage());
        }
        User user= (User) redisUtil.get("login_admin");
        return mapper.writeValueAsString(info);
    }
}
