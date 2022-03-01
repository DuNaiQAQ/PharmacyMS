package com.jsu.pharmacyms.service.Impl;

import com.jsu.pharmacyms.dao.DrugDao;
import com.jsu.pharmacyms.domain.DrugInfo;
import com.jsu.pharmacyms.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrugServiceImpl implements DrugService {
    @Autowired
    protected DrugDao dao;

    @Override
    public void addDrugInfo(DrugInfo info) {
        dao.addDrugInfo(info);
    }

    @Override
    public boolean hasDrugName(String name) {
        return dao.HaveName(name) == 1;
    }
}
