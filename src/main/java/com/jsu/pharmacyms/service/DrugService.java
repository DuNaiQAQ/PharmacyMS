package com.jsu.pharmacyms.service;

import com.jsu.pharmacyms.domain.CategoryInfo;
import com.jsu.pharmacyms.domain.DrugInfo;
import com.jsu.pharmacyms.domain.DrugsPurchase;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DrugService {
    void addDrugInfo(DrugInfo info);
    void delDrugInfo(int id);
    List<DrugInfo> getDrugInfo();
    void updateDrugInfo(DrugInfo info);
    DrugInfo getDrugById(int id);
    void addNewCategory(CategoryInfo info);
    void delCateory(int id);
    void updateCategory(CategoryInfo info);
    List<CategoryInfo> getCategories();
    void addNum(int id,int num);
    void delNum(int id,int num);
    List<CategoryInfo> selectFatherCategory();
    List<CategoryInfo> getChildList(int id);
    List<DrugsPurchase> getByNameType(String name,int type);
    List<DrugsPurchase> getByName(String name);
    List<DrugsPurchase> getByIdType(int id,int type);
    List<DrugsPurchase> getById(int id);
}
