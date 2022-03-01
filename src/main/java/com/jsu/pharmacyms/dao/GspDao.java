package com.jsu.pharmacyms.dao;

import com.jsu.pharmacyms.domain.GspCheckInfo;
import com.jsu.pharmacyms.domain.GspRejectInfo;
import com.jsu.pharmacyms.domain.GspReturnInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GspDao {
    @Insert("insert into gsp_check(id,position,temper,drug_batches,status,check_time)" +
            "values(id,position,temper,drug_batches,status,check_time)")
    void addGspCheckInfo(GspCheckInfo info);

    @Insert("insert into gsp_reject(id,batch_id,durg_id,check_time,reason)" +
            "values(#{id},#{batch_id},#{drug_id},#{check_time},#{reason} )")
    void addGspRejectInfo(GspRejectInfo info);

    @Insert("insert into gsp_return(id,drug_id,batch_id,is_return,return_time)" +
            "values(#{id},#{drug_id},#{batch_id},#{is_return},#{return_time})")
    void addGspReturnInfo(GspReturnInfo info);

    @Select("select * from gsp_check")
    List<GspCheckInfo> getCheckInfos();

    @Select("select * from gsp_reject")
    List<GspRejectInfo> getRejectInfos();

    @Select("select * from gsp_return")
    List<GspRejectInfo> getReturnInfos();

    @Update("update gsp_check" +
            "set position=#{position},temper=#{temper},drug_batches=#{drug_batches},status=#{status},check_time=#{check_time}" +
            "where id = #{id}")
    void updateCheckInfo(GspCheckInfo info);

    @Update("update gsp_reject" +
            "set batch_id=#{batch_id},drug_id=#{drug_id},check_time=#{check_time},reason=#{reason}" +
            "where id = #{id}")
    void updateRejectInfo(GspRejectInfo info);

    @Update("update gsp_return" +
            "set drug_id=#{drug_id},batch_id=#{batch_id},is_return=#{is_return},return_time=#{return_time}" +
            "where id = #{id}")
    void updateReturnInfo(GspReturnInfo info);

    @Delete("delete * from gsp_check where id = #{id}")
    void deleteCheckInfo(int id);

    @Delete("delete * from gsp_reject where id = #{id}")
    void deleteRejectInfo(int id);

    @Delete("delete * from gsp_return where id = #{id}")
    void deleteReturnInfo(int id);
}
