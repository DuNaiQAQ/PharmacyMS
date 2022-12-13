package com.jsu.pharmacyms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsu.pharmacyms.domain.ResultInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/upload")
@CrossOrigin
public class UploadController {
    @Value("${spring.servlet.multipart.location}")
    private String uploadPathImg;

    @RequestMapping(value = "/head",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String uploadHead(@RequestParam("file")MultipartFile file, int id, String name, HttpServletRequest request){
        try{
            byte[] bytes= file.getBytes();
            String imageFileName=id+"-"+name+".jpg";
            String path=uploadPathImg;
            File imgFile=new File(path);
            if(!imgFile.exists()){
                imgFile.mkdirs();
            }
            file.transferTo(new File(imgFile,imageFileName));
            System.out.println(path+imageFileName);
            return "http://localhost:8080/img/"+imageFileName;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/factory",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String uploadFactory(@RequestParam("file")MultipartFile file, String name, HttpServletRequest request){
        try{
            byte[] bytes= file.getBytes();
            String imageFileName=name+".jpg";
            String path=uploadPathImg;
            File imgFile=new File(path);
            if(!imgFile.exists()){
                imgFile.mkdirs();
            }
            file.transferTo(new File(imgFile,imageFileName));
            System.out.println(path+imageFileName);
            return "http://localhost:8080/img/"+imageFileName;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
