package com.jsu.pharmacyms.dao;

import com.jsu.pharmacyms.domain.DrugOut;
import com.jsu.pharmacyms.domain.DrugsPurchase;
import org.apache.ibatis.annotations.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface StoreHouseDao {
    @Insert("insert into drug_store(id,drug_id,product_fac_id,produce_time,validity,purchase_num,purchase_value,purchase_value_sum,purchase_time)" +
            "values(#{id},#{drug_id},#{product_fac_id},#{produce_time},#{validity},#{purchase_num},#{purchase_value},#{purchase_value_sum},#{purchase_time})")
        void purchaseDrug(DrugsPurchase info);

    @Delete("delete from drug_store where id = #{id}")
    void del_purchase_info(int id);

    @Update("update drug_store set" +
            "id=#{id},drug_id=#{drug_id},produce_time=#{produce_time},validity=#{validity},product_fac_id=#{product_fac_id}," +
            "purchase_num=#{purchase_num},purchase_value=#{purchase_value},purchase_value_sum=#{purchase_value_sum}," +
            "purchase_time=#{purchase_time}")
    void update_purchase_info(DrugsPurchase info);

    @Select("select drug_store.*,drug_info.name as 'drug_name',drug_produce_fac.name as 'product_fac_name' " +
            "from drug_store,drug_info,drug_produce_fac " +
            "where drug_store.drug_id = drug_info.id and drug_produce_fac.id = drug_store.product_fac_id")
    List<DrugsPurchase> getPurchaseInfos();

    @Select("select sh_out.* from sh_out")
    List<DrugOut> getOutInfo();

    @Insert("insert into sh_out(id,drug_id,batch_id,out_num,value,out_sum,out_time)" +
            "values(#{id},#{drug_id},#{batch_id},#{out_num},#{value},#{out_sum},#{out_time})")
    void addOutInfo(DrugOut info);

    @Delete("delete from sh_out where id = #{id}")
    void del_outinfo(int id);

    @Update("update sh_out set " +
            "id=#{id},drug_id=#{drug_id},batch_id=#{batch_id},out_num=#{out_num},value=#{value},out_sum=#{out_sum},out_time=#{out_time}" +
            "where id = #{id}")
    void update_out_info(DrugOut info);

    @Select("select TIMESTAMPDIFF(DAY,#{date2},#{date1})")
    int getDiff(Timestamp date1, Timestamp date2);
}
