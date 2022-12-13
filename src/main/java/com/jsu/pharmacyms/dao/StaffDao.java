package com.jsu.pharmacyms.dao;

import com.jsu.pharmacyms.domain.Staff;
import com.jsu.pharmacyms.domain.StaffType;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>员工类Dao层</p>
 * */
@Mapper
public interface StaffDao {

    /**
     * <p>添加新员工信息</p>
     * @param staff 提交表单的员工信息
     * */
    @Insert("insert into staff(id,name,sex,birthday,phone,address,photo,type,user_id) " +
            "values(#{id},#{name},#{sex},#{birthday},#{phone},#{address},#{photo},#{type},#{id})")
    void addStaff(Staff staff);

    /**
     * <p>通过员工ID查找信息</p>
     * @param id 员工ID
     * @return 员工信息
     * */
    @Select("select * from staff where id = #{id}")
    Staff getStaff(int id);

    /**
     * <p>获取所有员工数据</p>
     * @return 所有员工数据
     * */
    @Select("select staff.*,(select name from staff_type where staff.type = staff_type.id)  as 'type_name' from staff")
    List<Staff> getStaffList();

    /**
     * <p>获取职工类型数据</p>
     * @return 职工类型
     * */
    @Select("select * from staff_type")
    List<StaffType> getTypeList();

    /**
     * <p>添加职工类型</p>
     * @param type 职工类型
     * */
    @Insert("insert into staff_type(name) values(#{name})")
    void addType(StaffType type);

    /**
     * <p>删除员工信息</p>
     * @param id 员工ID
     * */
    @Delete("delete from staff where id = #{id}")
    void deleteStaff(int id);

    /**
     * <p>删除职称信息</p>
     * @param id 职称ID
     * */
    @Delete("delete from staff_type where id = #{id}")
    void deleteType(int id);

    @Select("select * from staff_type where id = #{id}")
    StaffType getTypeInfo(int id);

    @Insert("insert into staff_type(name) values(#{name})")
    void addTypeInfo(StaffType type);

    @Update("update staff_type set name = #{name} where id = #{id}")
    void updateTypeInfo(StaffType staffType);

    @Update("update staff " +
            "set id=#{id},name=#{name},sex=#{sex},birthday=#{birthday},phone=#{phone},address=#{address},photo=#{photo},type=#{type},user_id=#{user_id} " +
            "where id = #{id}")
    void updateStaff(Staff info);
}
