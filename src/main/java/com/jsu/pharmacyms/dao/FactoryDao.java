package com.jsu.pharmacyms.dao;

import com.jsu.pharmacyms.domain.Factory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FactoryDao {
    @Insert("insert into drug_produce_fac(id,name,about,logo,contact_number,contact_name)" +
            "values(#{id},#{name},#{about},#{logo},#{contact_number},#{contact_name})")
    void addFactoryinfo(Factory info);

    @Update("update drug_produce_fac" +
            "set name=#{name},about=#{about},logo=#{logo},contact_number=#{contact_number},contact_name=#{contact_name}" +
            "where id = #{id}")
    void updateFactoryInfo(Factory info);

    @Select("select * from drug_produce_fac")
    List<Factory> getFactoryInfos();

    @Delete("delete from drug_produce_fac where id = #{id}")
    void deleteFactoryInfo(int id);
}
