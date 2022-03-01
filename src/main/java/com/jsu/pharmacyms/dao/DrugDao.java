package com.jsu.pharmacyms.dao;

import com.jsu.pharmacyms.domain.CategoryInfo;
import com.jsu.pharmacyms.domain.DrugInfo;
import com.jsu.pharmacyms.domain.DrugsPurchase;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DrugDao {
    /**
     * <p>直接通过药品名称来获得药品信息</p>
     * @param name 药品名称
     * @return 药品信息
     * */
    @Select("select * from drug_info where id = #{id}")
    DrugInfo getDrugByName(String name);

    /**
     * <p>创建新药品信息</p>
     * @param drugInfo 药品信息
     * */
    @Insert("insert into drug_info(id,name,category_id,stock_num,position,unit,specification,tips,value)" +
            "values(#{id},#{name},#{category_id},#{stock_num},#{position},#{unit},#{specification},#{tips},#{value})")
    void addDrugInfo(DrugInfo drugInfo);

    /**
     * <p>更新药品信息</p>
     * @param drugInfo 药品信息
     * */
    @Update("update drug_info " +
            "set id=#{id},name=#{name},category_id=#{category_id},stock_num=#{stock_num},position=#{position},unit=#{unit}," +
            "specification=#{specification},tips=#{tips},value=#{value}" +
            "where id = #{id}")
    void updateInfo(DrugInfo drugInfo);

    @Delete("delete * from drug_info where id = #{id}")
    void deleteinfo(int id);

    @Select("select * from drug_info")
    List<DrugInfo> getInfos();

    @Update("update drug_info set stock_num = #{num}")
    public void updateNum(int num);

    //这一部分之后的内容是药品分类的内容
    @Insert("insert into drug_category(id,name,parent_id)" +
            "values(#{id},#{name},#{parent_id}")
    void addNewCategory(CategoryInfo info);

    @Update("update drug_category set id=#{id},name=#{name},parent_id=#{parent_id} where id=#{id}")
    void updateCategoryInfo(CategoryInfo info);

    @Delete("delete * from drug_category where id = #{id}")
    void deleteCategory(int id);

    @Select("select * from drug_category")
    List<CategoryInfo> getCategories();
}
