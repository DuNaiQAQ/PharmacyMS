package com.jsu.pharmacyms.service.Impl;

import com.jsu.pharmacyms.dao.DrugDao;
import com.jsu.pharmacyms.domain.CategoryInfo;
import com.jsu.pharmacyms.domain.DrugInfo;
import com.jsu.pharmacyms.domain.DrugsPurchase;
import com.jsu.pharmacyms.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugServiceImpl implements DrugService {
    @Autowired
    protected DrugDao dao;

    @Override
    public void addDrugInfo(DrugInfo info) {
        dao.addDrugInfo(info);
    }

    @Override
    public void delDrugInfo(int id) {
        dao.deleteinfo(id);
    }

    @Override
    public List<DrugInfo> getDrugInfo() {
        return dao.getInfos();
    }

    @Override
    public void updateDrugInfo(DrugInfo info) {
        dao.updateInfo(info);
    }

    @Override
    public DrugInfo getDrugById(int id) {
        return dao.getInfoById(id);
    }

    @Override
    public void addNewCategory(CategoryInfo info) {
        dao.addNewCategory(info);
    }

    @Override
    public void delCateory(int id) {
        dao.deleteCategory(id);
    }

    @Override
    public void updateCategory(CategoryInfo info) {
        dao.updateCategoryInfo(info);
    }

    @Override
    public List<CategoryInfo> getCategories() {
        return dao.getCategories();
    }

    @Override
    public void addNum(int id, int num) {
        dao.UpdateAddNum(num,id);
    }

    @Override
    public void delNum(int id, int num) {
        dao.UpdateDelNum(num,id);
    }

    @Override
    public List<CategoryInfo> selectFatherCategory() {
        return dao.selectFatherCategory();
    }

    @Override
    public List<CategoryInfo> getChildList(int id) {
        return dao.getChildList(id);
    }

    @Override
    public List<DrugsPurchase> getByNameType(String name, int type) {
        return dao.getDrugByTypeAndName(type,name);
    }

    @Override
    public List<DrugsPurchase> getByName(String name) {
        return dao.getDrugByName(name);
    }

    @Override
    public List<DrugsPurchase> getByIdType(int id, int type) {
        return dao.searchId(id,type);
    }

    @Override
    public List<DrugsPurchase> getById(int id) {
        return dao.searchId_Notype(id);
    }
}
