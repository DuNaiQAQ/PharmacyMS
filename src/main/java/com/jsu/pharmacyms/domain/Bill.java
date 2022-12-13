package com.jsu.pharmacyms.domain;

import java.sql.Timestamp;

public class Bill {
    private int id;//账单ID
    private String bill_info;//账单信息
    private double value;//账单金额
    private Timestamp bill_time;//账单时间
    private int bill_type;//账单类型 1:入库 2:售出

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBill_info() {
        return bill_info;
    }

    public void setBill_info(String bill_info) {
        this.bill_info = bill_info;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Timestamp getBill_time() {
        return bill_time;
    }

    public void setBill_time(Timestamp bill_time) {
        this.bill_time = bill_time;
    }

    public int getBill_type() {
        return bill_type;
    }

    public void setBill_type(int bill_type) {
        this.bill_type = bill_type;
    }
}
