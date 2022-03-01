package com.jsu.pharmacyms.service.Impl;

import com.jsu.pharmacyms.dao.StoreHouseDao;
import com.jsu.pharmacyms.domain.DrugOut;
import com.jsu.pharmacyms.domain.DrugsPurchase;
import com.jsu.pharmacyms.service.StoreHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreHouseServiceImpl implements StoreHouseService {
    @Autowired
    protected StoreHouseDao storeHouseDao;

    @Override
    public void purchaseDrug(DrugsPurchase info) {
        storeHouseDao.purchaseDrug(info);
    }

    @Override
    public void del_purchase_info(int id) {
        storeHouseDao.del_purchase_info(id);
    }

    @Override
    public void del_out_info(int id) {
        storeHouseDao.del_outinfo(id);
    }

    @Override
    public void addOutInfo(DrugOut info) {
        storeHouseDao.addOutInfo(info);
    }

    @Override
    public void updateOutInfo(DrugOut info) {
        storeHouseDao.update_out_info(info);
    }

    @Override
    public void updatePurchaseInfo(DrugsPurchase info) {
        storeHouseDao.update_purchase_info(info);
    }

    @Override
    public List<DrugOut> getOut() {
        return storeHouseDao.getOutInfo();
    }

    @Override
    public List<DrugsPurchase> getPurchase() {
        return storeHouseDao.getPurchaseInfos();
    }
}
