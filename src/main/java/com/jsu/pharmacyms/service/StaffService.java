package com.jsu.pharmacyms.service;

import com.jsu.pharmacyms.domain.Staff;
import com.jsu.pharmacyms.domain.StaffType;

import java.util.List;

public interface StaffService {
    Staff getStaff(int id);
    void addStaff(Staff staff);
    List<StaffType> getTypeList();
    List<Staff> getStaffList();
    void addConnection(int s_id,int t_id);
    void deleteStaff(int id);
    void deleteType(int id);
    StaffType getTypeInfo(int id);
}
