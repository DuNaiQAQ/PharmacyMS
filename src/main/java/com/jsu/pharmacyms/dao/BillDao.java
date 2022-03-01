package com.jsu.pharmacyms.dao;

import com.jsu.pharmacyms.domain.Bill;
import com.jsu.pharmacyms.domain.SoldInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BillDao {
    @Select("")
    void getMonthInfo();

    @Insert("insert into bill(id,bill_info,value,bill_time,bill_type)" +
            "values(#{id},#{bill_info},#{value},#{bill_time},#{bill_type})")
    void addNewInfo(Bill info);

    @Select("select * from bill")
    List<Bill> getBills();

    @Insert("insert into sold_info(id,drug_id,batch_id,sold_num,sold_sum,sold_time,sold_user)" +
            "values(id,drug_id,batch_id,sold_num,sold_sum,sold_time,sold_user)")
    void addSoldInfo(SoldInfo info);

    @Select("select * from sold_info")
    List<SoldInfo> getSoldInfos();
}
