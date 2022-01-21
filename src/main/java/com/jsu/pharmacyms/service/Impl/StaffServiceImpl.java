package com.jsu.pharmacyms.service.Impl;

import com.jsu.pharmacyms.dao.StaffDao;
import com.jsu.pharmacyms.domain.Staff;
import com.jsu.pharmacyms.domain.StaffType;
import com.jsu.pharmacyms.service.StaffService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    protected StaffDao staffDao;

    /**
     * <p>查询某员工数据</p>
     * @param id 员工ID
     * @return 员工数据
     * */
    @Override
    public Staff getStaff(int id) {
        return staffDao.getStaff(id);
    }

    /**
     * <p>添加某个员工的信息</p>
     * @param staff 员工数据
     * */
    @Override
    public void addStaff(Staff staff) {
        staffDao.addStaff(staff);
    }

    /**
     * <p>获取员工职称数据</p>
     * @return 员工职称列表
     * */
    @Override
    public List<StaffType> getTypeList() {
        return staffDao.getTypeList();
    }

    /**
     * <p>获取所有员工信息</p>
     * @return 员工信息列表
     * */
    @Override
    public List<Staff> getStaffList() {
        return staffDao.getStaffList();
    }

    /**
     * <p>添加员工职称连接</p>
     * @param s_id 员工ID
     * @param t_id 职称ID
     * */
    @Override
    public void addConnection(int s_id,int t_id) {
        staffDao.addConnection(t_id,s_id);
    }

    /**
     * <p>删除员工信息</p>
     * @param id 员工ID
     * */
    @Override
    public void deleteStaff(int id) {
        staffDao.deleteStaff(id);
    }

    /**
     * <p>删除职称信息</p>
     * @param id 职称ID
     * */
    @Override
    public void deleteType(int id) {
        staffDao.deleteType(id);
    }

    /**
     * <p>获取职工职称信息</p>
     * @param id 职称ID
     * @return 职称信息
     * */
    @Override
    public StaffType getTypeInfo(int id) {
        return staffDao.getTypeInfo(id);
    }


}
