package com.jsu.pharmacyms.domain;

import java.sql.Timestamp;

public class DrugOut {
    private int id;//事件ID
    private int drug_id;//药品ID
    private int batch_id;
    private String drug_name;//药品名称
    private int out_num;//出库数量
    private double value;//出库单价
    private double out_sum;//出库总额
    private Timestamp out_time;//出库日期

    public int getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(int batch_id) {
        this.batch_id = batch_id;
    }

    public Timestamp getOut_time() {
        return out_time;
    }

    public void setOut_time(Timestamp out_time) {
        this.out_time = out_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDrug_id() {
        return drug_id;
    }

    public void setDrug_id(int drug_id) {
        this.drug_id = drug_id;
    }

    public String getDrug_name() {
        return drug_name;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

    public int getOut_num() {
        return out_num;
    }

    public void setOut_num(int out_num) {
        this.out_num = out_num;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getOut_sum() {
        return out_sum;
    }

    public void setOut_sum(double out_sum) {
        this.out_sum = out_sum;
    }
}
