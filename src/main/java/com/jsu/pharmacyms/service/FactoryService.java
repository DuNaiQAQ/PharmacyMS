package com.jsu.pharmacyms.service;

import com.jsu.pharmacyms.domain.Factory;

import java.util.List;

public interface FactoryService {
    void addFactoryInfo(Factory info);
    void updateFactoryInfo(Factory info);
    void deleteFactoryInfo(int id);
    List<Factory> getFactoryInfos();
}
