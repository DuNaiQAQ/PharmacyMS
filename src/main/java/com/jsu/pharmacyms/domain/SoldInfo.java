package com.jsu.pharmacyms.domain;

import java.sql.Timestamp;

public class SoldInfo {
    private int id;//主键
    private int drug_id;//药品ID
    private String drug_name;
    private int value;
    private int batch_id;//批次ID
    private int sold_num;//售出数量
    private double sold_sum;//售出总额
    private Timestamp sold_time;//出售时间
    private String sold_user;//出售用户

    public String getDrug_name() {
        return drug_name;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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

    public int getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(int batch_id) {
        this.batch_id = batch_id;
    }

    public int getSold_num() {
        return sold_num;
    }

    public void setSold_num(int sold_num) {
        this.sold_num = sold_num;
    }

    public double getSold_sum() {
        return sold_sum;
    }

    public void setSold_sum(double sold_sum) {
        this.sold_sum = sold_sum;
    }

    public Timestamp getSold_time() {
        return sold_time;
    }

    public void setSold_time(Timestamp sold_time) {
        this.sold_time = sold_time;
    }

    public String getSold_user() {
        return sold_user;
    }

    public void setSold_user(String sold_user) {
        this.sold_user = sold_user;
    }
}
