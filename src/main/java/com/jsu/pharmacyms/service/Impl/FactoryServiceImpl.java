package com.jsu.pharmacyms.service.Impl;

import com.jsu.pharmacyms.dao.FactoryDao;
import com.jsu.pharmacyms.domain.Factory;
import com.jsu.pharmacyms.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoryServiceImpl implements FactoryService {

    @Autowired
    protected FactoryDao factoryDao;

    @Override
    public void addFactoryInfo(Factory info) {
        factoryDao.addFactoryinfo(info);
    }

    @Override
    public void updateFactoryInfo(Factory info) {
        factoryDao.updateFactoryInfo(info);
    }

    @Override
    public void deleteFactoryInfo(int id) {
        factoryDao.deleteFactoryInfo(id);
    }

    @Override
    public List<Factory> getFactoryInfos() {
        return factoryDao.getFactoryInfos();
    }
}
