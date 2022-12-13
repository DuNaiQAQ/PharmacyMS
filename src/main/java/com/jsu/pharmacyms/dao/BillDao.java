package com.jsu.pharmacyms.dao;

import com.jsu.pharmacyms.domain.Bill;
import com.jsu.pharmacyms.domain.Caculate;
import com.jsu.pharmacyms.domain.SoldInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface BillDao {
    @Select("")
    void getMonthInfo();

    @Insert("insert into bill(bill_info,value,bill_time,bill_type)" +
            "values(#{bill_info},#{value},#{bill_time},#{bill_type})")
    void addNewInfo(double value, String bill_info, Timestamp bill_time, int bill_type);

    @Select("select * from bill")
    List<Bill> getBills();

    @Insert("insert into sold_info(id,drug_id,batch_id,sold_num,sold_sum,sold_time,sold_user)" +
            "values(#{id},#{drug_id},#{batch_id},#{sold_num},#{sold_sum},#{sold_time},#{sold_user})")
    void addSoldInfo(SoldInfo info);

    @Select("select * from sold_info")
    List<SoldInfo> getSoldInfos();

    @Update("update drug_store set purchase_num = purchase_num - #{num} where id = #{batch_id}")
    void SellOutDrug_A(int batch_id, int num);

    @Update("update drug_info set stock_num = stock_num - #{num} where id = #{id}")
    void SellOutDrug_B(int id, int num);

    @Select("select distinct date(a.temp_time) as 'time',ifnull(b.value,0) as 'out',ifnull(c.value,0) as 'in'\n" +
            "from(\n" +
            "select date(bill_time) as temp_time from bill\n" +
            ")a left join (\n" +
            "select date(bill_time) as time,sum(value) as value from bill\n" +
            "where bill_type = 1\n" +
            "group by time\n" +
            ")b on a.temp_time=b.time left join (\n" +
            "select date(bill_time) as time,sum(value) as value from bill\n" +
            "where bill_type = 2\n" +
            "group by time\n" +
            ")c on a.temp_time=c.time\n" +
            "order by date(a.temp_time) DESC")
    List<Caculate> getInfos_date();

    @Select("select distinct CONCAT(a.temp_ytime,'-',a.temp_mtime)as 'time',ifnull(b.value,0) as 'out',ifnull(c.value,0) as 'in'\n" +
            "from(\n" +
            "select year(bill_time) as temp_ytime,month(bill_time) as temp_mtime from bill\n" +
            ")a left join (\n"+
            "select year(bill_time) as ytime,month(bill_time) as mtime,sum(value) as value from bill\n"+
            "where bill_type = 1\n"+
            "group by year(bill_time),month(bill_time)\n"+
            ")b on a.temp_ytime=b.ytime and a.temp_mtime=b.mtime left join (\n"+
            "select year(bill_time) as ytime,month(bill_time) as mtime,sum(value) as value from bill\n"+
            "where bill_type = 2\n"+
            "group by year(bill_time),month(bill_time)\n"+
            ")c on a.temp_ytime=c.ytime and a.temp_mtime=c.mtime\n"+
            "order by time DESC")
    List<Caculate> getInfos_month();
}
