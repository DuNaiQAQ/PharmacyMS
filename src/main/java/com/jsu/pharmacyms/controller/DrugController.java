package com.jsu.pharmacyms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsu.pharmacyms.domain.*;
import com.jsu.pharmacyms.service.DrugService;
import com.jsu.pharmacyms.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/drug")
@CrossOrigin
public class DrugController {
    @Autowired
    protected DrugService drugService;

    @Autowired
    protected RedisUtil redisUtil;


    @RequestMapping("/adddrug")
    @ResponseBody
    public String addNewDrug(@RequestBody DrugInfo info)throws JsonProcessingException {
        ResultInfo inf=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            drugService.addDrugInfo(info);
            inf.setCode(200);
            inf.setMessage("添加药品信息成功");
        }catch (Exception e){
            inf.setMessage("错误:"+e.getMessage());
            inf.setCode(500);
        }
        User user= (User) redisUtil.get("login_admin");
        return mapper.writeValueAsString(inf);
    }

    @RequestMapping("/deldrug")
    @ResponseBody
    public String delDrug(@RequestBody int id)throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            drugService.delDrugInfo(id);
            info.setCode(200);
            info.setMessage("删除药品信息成功");
        }catch (Exception e){
            info.setMessage("错误:"+e.getMessage());
            info.setCode(500);
        }
        User user= (User) redisUtil.get("login_admin");
        return mapper.writeValueAsString(info);
    }

    @RequestMapping("/updatedrug")
    @ResponseBody
    public String updateDrug(@RequestBody DrugInfo drugInfo)throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            drugService.updateDrugInfo(drugInfo);
            info.setCode(200);
            info.setMessage("修改药品信息成功");
        }catch (Exception e){
            info.setMessage("错误:"+e.getMessage());
            info.setCode(500);
        }
        User user= (User) redisUtil.get("login_admin");
        return mapper.writeValueAsString(info);
    }

    @RequestMapping("/getdrugs")
    @ResponseBody
    public String getDrugsInfo()throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            DataList dataList=new DataList();
            dataList.setItems(drugService.getDrugInfo());
            dataList.setItemNums(dataList.getItems().size());
            info.setRes(dataList);
            info.setCode(200);
            info.setMessage("获取药品信息成功");
        }catch (Exception e){
            info.setMessage("错误:"+e.getMessage());
            info.setCode(500);
        }
        return mapper.writeValueAsString(info);
    }


    @RequestMapping("/addcategory")
    @ResponseBody
    public String addcategory(@RequestBody CategoryInfo info)throws JsonProcessingException {
        ResultInfo inf=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            drugService.addNewCategory(info);
            inf.setCode(200);
            inf.setMessage("添加信息成功");
        }catch (Exception e){
            inf.setMessage("错误:"+e.getMessage());
            inf.setCode(500);
        }
        User user= (User) redisUtil.get("login_admin");
        return mapper.writeValueAsString(inf);
    }

    @RequestMapping("/delcategory")
    @ResponseBody
    public String delcategory(@RequestBody int id)throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            drugService.delCateory(id);
            info.setCode(200);
            info.setMessage("删除信息成功");
        }catch (Exception e){
            info.setMessage("错误:"+e.getMessage());
            info.setCode(500);
        }
        User user= (User) redisUtil.get("login_admin");
        return mapper.writeValueAsString(info);
    }

    @RequestMapping("/updatecategory")
    @ResponseBody
    public String updatecategory(@RequestBody CategoryInfo categoryInfo)throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            drugService.updateCategory(categoryInfo);
            info.setCode(200);
            info.setMessage("修改信息成功");
        }catch (Exception e){
            info.setMessage("错误:"+e.getMessage());
            info.setCode(500);
        }
        User user= (User) redisUtil.get("login_admin");
        return mapper.writeValueAsString(info);
    }

    @RequestMapping("/getcategory")
    @ResponseBody
    public String getcategoryInfos()throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        try{
            DataList dataList=new DataList();
            dataList.setItems(drugService.getCategories());
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

    @RequestMapping("/getcategory_form")
    @ResponseBody
    public String getcategory_form()throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        List<CategoryServerBack> backList=new ArrayList<>();
        try{
            List<CategoryInfo> temp_infos=drugService.selectFatherCategory();
            for(int i=0;i<temp_infos.size();i++){
                CategoryServerBack temp_info=new CategoryServerBack();
                temp_info.setLabel(temp_infos.get(i).getName());
                temp_info.setOption(drugService.getChildList(temp_infos.get(i).getId()));
                backList.add(temp_info);
            }
            info.setCode(200);
            info.setMessage("获取数据成功");
            DataList dataList=new DataList();
            dataList.setItems(backList);
            info.setRes(dataList);
        }catch (Exception e){
            info.setCode(500);
            info.setMessage("服务器错误");
        }
        return mapper.writeValueAsString(info);
    }

    @RequestMapping("/searching")
    @ResponseBody
    public String searchDrug(@RequestBody SearchInfo searchInfo)throws JsonProcessingException{
        ResultInfo info=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        String regex="^[0-9]*$";
        Pattern pattern=Pattern.compile(regex);
        int id=0;
        if(!searchInfo.getType().equals("")) {
            id = Integer.parseInt(searchInfo.getType());
        }
        try{
            DataList dataList=new DataList();
            Matcher matcher=pattern.matcher(searchInfo.getName());
            if(matcher.find()){//是不是数字，是则通过ID搜索，这里有三种搜索方法
                if(id == 0){
                    dataList.setItems(drugService.getById(Integer.parseInt(searchInfo.getName())));
                }else {
                    dataList.setItems(drugService.getByIdType(Integer.parseInt(searchInfo.getName()), id));
                }
            }else{//若不是，则执行其他操作
                if(id ==0){
                    dataList.setItems(drugService.getByName(searchInfo.getName()));
                }else {
                    dataList.setItems(drugService.getByNameType(searchInfo.getName(), id));
                }
            }
            dataList.setItemNums(dataList.getItems().size());
            info.setRes(dataList);
            info.setCode(200);
            info.setMessage("获取数据成功");
        }catch (Exception e){
            e.printStackTrace();
            info.setCode(500);
            info.setMessage("服务器错误");
        }
        return mapper.writeValueAsString(info);
    }
}
