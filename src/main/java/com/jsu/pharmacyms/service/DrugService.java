package com.jsu.pharmacyms.service;

import com.jsu.pharmacyms.domain.DrugInfo;

public interface DrugService {
    void addDrugInfo(DrugInfo info);
    boolean hasDrugName(String name);
}
