package com.jsu.pharmacyms.service;

import com.jsu.pharmacyms.domain.DrugOut;
import com.jsu.pharmacyms.domain.DrugsPurchase;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public interface StoreHouseService {
     void purchaseDrug(DrugsPurchase info);
     void del_purchase_info(int id);
     void del_out_info(int id);
     void addOutInfo(DrugOut info);
     void updateOutInfo(DrugOut info);
     void updatePurchaseInfo(DrugsPurchase info);
     List<DrugOut> getOut();
     List<DrugsPurchase> getPurchase();
     int getDiffTime(Timestamp start,Timestamp end);
}
