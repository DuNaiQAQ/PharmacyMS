package com.jsu.pharmacyms.service;

import com.jsu.pharmacyms.domain.Bill;
import com.jsu.pharmacyms.domain.Caculate;
import com.jsu.pharmacyms.domain.SoldInfo;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public interface BillService {
    void addBillInfo(String bill_info, double value, Timestamp bill_time, int bill_type);
    void addSoldInfo(SoldInfo info);
    void SoldDrug(int id, int num,int batch_id);
    List<SoldInfo> getSellInfo();
    List<Bill> getBillInfo();
    List<Caculate> getInfo_date();
    List<Caculate> getInfo_month();
}
