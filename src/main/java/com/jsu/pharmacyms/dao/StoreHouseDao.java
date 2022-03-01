package com.jsu.pharmacyms.dao;

import com.jsu.pharmacyms.domain.DrugOut;
import com.jsu.pharmacyms.domain.DrugsPurchase;
import com.jsu.pharmacyms.domain.PurchaseProject;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StoreHouseDao {
    @Insert("insert into sh_purchase(id,drug_id,product_fact_id,produce_time,validity,purchase_num,purchase_value,purchase_value_sum,purchase_date)" +
            "values(#{id},#{drug_id},#{product_fact_id},#{produce_time},#{validity},#{purchase_num},#{purchase_value},#{purchase_value_sum},#{purchase_date})")
        void purchaseDrug(DrugsPurchase info);

    @Delete("delete * from sh_purchase where id = #{id}")
    void del_purchase_info(int id);

    @Update("update sh_purchase set" +
            "id=#{id},drug_id=#{drug_id},produce_time=#{produce_time},validity=#{validity},product_fact_id=#{product_fact_id}," +
            "purchase_num=#{purchase_num},purchase_value=#{purchase_value},purchase_value_sum=#{purchase_value_sum}," +
            "purchase_date=#{purchase_date}")
    void update_purchase_info(DrugsPurchase info);

    @Select("select sh_purchase.* from sh_purchase")
    List<DrugsPurchase> getPurchaseInfos();

    //TODO 更正SQL语句
    @Select("select sh_out.* from sh_out")
    List<DrugOut> getOutInfo();

    @Insert("insert into sh_out(id,drug_id,batch_id,out_num,value,out_sum)" +
            "values(#{id},#{drug_id},#{batch_id},#{out_num},#{value},#{out_sum})")
    void addOutInfo(DrugOut info);

    @Delete("delete * from sh_out where id = #{id}")
    void del_outinfo(int id);

    @Update("update sh_out set " +
            "id=#{id},drug_id=#{drug_id},batch_id=#{batch_id},out_num=#{out_num},value=#{value},out_sum=#{out_sum}" +
            "where id = #{id}")
    void update_out_info(DrugOut info);


    @Insert("insert into sh_purchase_project(id,drug_id,purchase_num,is_purchase)" +
            "values(#{id},#{drug_id},#{purchase_num},#{is_purchase}")
    void addPorject(PurchaseProject project);

    @Update("update sh_purchase_project" +
            "set id=#{id},drug_id=#{drug_id},purchase_num={purchase_num},is_purchase=#{is_purchase}" +
            "where id = #{id}")
    void updateProject(PurchaseProject project);

    @Delete("delete * from sh_purchase_project where id = #{id}")
    void deleteProject(int id);

    @Select("select * from sh_purchase_project")
    List<PurchaseProject> getProjects();

}
