package com.jsu.pharmacyms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsu.pharmacyms.domain.*;
import com.jsu.pharmacyms.service.DrugService;
import com.jsu.pharmacyms.service.StoreHouseService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 仓库管理控制器
 * */
@Controller
@RequestMapping("/sh")
public class StoreHouseController {
    @Autowired
    protected DrugService drugService;

    @Autowired
    protected StoreHouseService storeHouseService;

    /**
     * <p>购入药品，输入批次信息，并且给总药品信息进行操作</p>
     * @param drugsPurchase 药品购入信息
     * @return 服务器响应数据
     * */
    @RequestMapping("/purchase")
    @ResponseBody
    public String purchase(DrugsPurchase drugsPurchase) throws JsonProcessingException {
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try {
            storeHouseService.purchaseDrug(drugsPurchase);
            info.setCode(200);
            info.setMessage("入库成功");
        }catch (Exception e){
            e.printStackTrace();
            info.setCode(500);
            info.setMessage("错误:"+e.getMessage());
        }
        return mapper.writeValueAsString(info);
    }


    /**
     * <p>删除入库信息</p>
     * @param id 药品批次ID
     * @return 服务器响应数据
     * */
    @RequestMapping("/purchase_del")
    @ResponseBody
    public String del_purchase(int id) throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            storeHouseService.del_purchase_info(id);
            info.setCode(200);
            info.setMessage("入库成功");
        }catch (Exception e){
            info.setCode(500);
            info.setMessage("错误:"+e.getMessage());
        }
        return mapper.writeValueAsString(info);
    }

    @RequestMapping("/purchase_change")
    @ResponseBody
    public String change_purchase(DrugsPurchase drugsPurchase) throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            storeHouseService.updatePurchaseInfo(drugsPurchase);
        }catch (Exception e){
            info.setCode(500);
            info.setMessage("错误:"+e.getMessage());
        }
        return mapper.writeValueAsString(info);
    }

    @RequestMapping("/out")
    @ResponseBody
    public String sh_out(DrugOut out_info) throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try {
            storeHouseService.addOutInfo(out_info);
            info.setCode(200);
            info.setMessage("添加记录成功");
        }catch (Exception e){
            info.setCode(500);
            info.setMessage("错误:"+e.getMessage());
        }
        return mapper.writeValueAsString(info);
    }

    @RequestMapping("/out_del")
    @ResponseBody
    public String sh_out_del(int id) throws JsonProcessingException{
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
}
