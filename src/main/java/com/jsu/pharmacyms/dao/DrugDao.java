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
     * @param name 药品名称模糊查询
     * @return 药品信息
     * */
    @Select("select drug_store.*,drug_produce_fac.name as 'product_fac_name',drug_info.name as 'drug_name',drug_info.value as 'value' " +
            "from drug_info,drug_store,drug_produce_fac " +
            "where drug_info.name like CONCAT('%',#{name},'%') and drug_info.id = drug_store.drug_id and drug_store.product_fac_id = drug_produce_fac.id")
    List<DrugsPurchase> getDrugByName(@Param("name") String name);

    @Select("select drug_store.*,drug_produce_fac.name as 'product_fac_name',drug_info.name as 'drug_name',drug_info.value as 'value' " +
            " from drug_info,drug_store,drug_produce_fac " +
            "where drug_info.name like CONCAT('%',#{name},'%') and drug_info.id = drug_store.drug_id and drug_info.category_id = #{type} and drug_store.product_fac_id = drug_produce_fac.id")
    List<DrugsPurchase> getDrugByTypeAndName(int type,@Param("name") String name);

    /**
     * <p>创建新药品信息</p>
     * @param drugInfo 药品信息
     * */
    @Insert("insert into drug_info(name,category_id,stock_num,position,unit,specification,tips,value)" +
            "values(#{name},#{category_id},#{stock_num},#{position},#{unit},#{specification},#{tips},#{value})")
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

    @Delete("delete from drug_info where id = #{id}")
    void deleteinfo(int id);

    @Select("select * from drug_info where name = #{id}")
    DrugInfo getInfoById(int id);

    @Select("select drug_info.*,drug_category.name as 'category_name' from drug_info,drug_category where drug_category.id=drug_info.category_id")
    List<DrugInfo> getInfos();

    @Update("update drug_info set stock_num = #{num}")
    public void updateNum(int num);

    //这一部分之后的内容是药品分类的内容
    @Insert("insert into drug_category(id,name,parent_id) " +
            "values(#{id},#{name},#{parent_id})")
    void addNewCategory(CategoryInfo info);

    @Update("update drug_category set id=#{id},name=#{name},parent_id=#{parent_id} where id=#{id}")
    void updateCategoryInfo(CategoryInfo info);

    @Delete("delete from drug_category where id = #{id}")
    void deleteCategory(int id);

    @Select("select * from drug_category")
    List<CategoryInfo> getCategories();

    @Update("update drug_info set stock_num = stock_num + #{num} where id = #{id}")
    void UpdateAddNum(int num,int id);

    @Update("update drug_info set stock_num = stock_num - #{num} where id = #{id}")
    void UpdateDelNum(int num,int id);

    @Select("select * from drug_category where parent_id = 0")
    List<CategoryInfo> selectFatherCategory();

    @Select("select * from drug_category where parent_id = #{id}")
    List<CategoryInfo> getChildList(int id);

    @Select("select drug_store.*,drug_produce_fac.name as 'product_fac_name',drug_info.name as 'drug_name',drug_info.value as 'value' " +
            "from drug_store,drug_info,drug_produce_fac " +
            "where drug_store.id = #{id} and drug_store.drug_id = drug_info.id and drug_info.category_id = #{type} and drug_store.product_fac_id = drug_produce_fac.id")
    List<DrugsPurchase> searchId(int id,int type);

    @Select("select drug_store.*,drug_produce_fac.name as 'product_fac_name',drug_info.name as 'drug_name',drug_info.value as 'value' " +
            "from drug_store,drug_info,drug_produce_fac " +
            "where drug_store.id = #{id} and drug_store.drug_id = drug_info.id and drug_store.product_fac_id = drug_produce_fac.id")
    List<DrugsPurchase> searchId_Notype(int id);
}
