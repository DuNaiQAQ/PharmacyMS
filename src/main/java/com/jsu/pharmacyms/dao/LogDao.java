package com.jsu.pharmacyms.dao;

import com.jsu.pharmacyms.domain.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LogDao {
    @Insert("insert into log(id,operation_type,things,time)" +
            "values(#{id},#{operation},#{type},#{things},#{time}")
    void addLog(Log log);

    @Select("select * from log")
    List<Log> getLogs();
}
