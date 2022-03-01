package com.jsu.pharmacyms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsu.pharmacyms.domain.DrugInfo;
import com.jsu.pharmacyms.domain.ResultInfo;
import com.jsu.pharmacyms.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/drug")
public class DrugController {
    @Autowired
    protected DrugService drugService;

    /**
     * <p>创建新药品信息</p>
     *
     * */
    @RequestMapping("/adddrug")
    @ResponseBody
    public String addNewDrug(DrugInfo info)throws JsonProcessingException {
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
        return mapper.writeValueAsString(inf);
    }
}
