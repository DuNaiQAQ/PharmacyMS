package com.jsu.pharmacyms.service.Impl;

import com.jsu.pharmacyms.dao.BillDao;
import com.jsu.pharmacyms.domain.Bill;
import com.jsu.pharmacyms.domain.Caculate;
import com.jsu.pharmacyms.domain.SoldInfo;
import com.jsu.pharmacyms.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    protected BillDao billDao;

    @Override
    public void addBillInfo(String bill_info, double value, Timestamp bill_time, int bill_type) {
        billDao.addNewInfo(value, bill_info, bill_time, bill_type);
    }

    @Override
    public void addSoldInfo(SoldInfo info) {
        billDao.addSoldInfo(info);
    }

    @Override
    public void SoldDrug(int id, int num,int batch_id) {
        billDao.SellOutDrug_A(batch_id,num);
        billDao.SellOutDrug_B(id,num);
    }

    @Override
    public List<SoldInfo> getSellInfo() {

        return billDao.getSoldInfos();
    }

    @Override
    public List<Bill> getBillInfo() {
        return billDao.getBills();
    }

    @Override
    public List<Caculate> getInfo_date() {
        return billDao.getInfos_date();
    }

    @Override
    public List<Caculate> getInfo_month() {
        return billDao.getInfos_month();
    }
}
