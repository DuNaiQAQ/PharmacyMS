package com.jsu.pharmacyms.domain;

import java.sql.Timestamp;

/**
 * <p>药品进货信息</p>
 * <p>要注意的是药品进货一般是以批次为主</p>
 * */
public class DrugsPurchase {
    private int id;//进货单ID
    private int drug_id;//药品名称
    private String drug_name;
    private String batch_id;//批次号
    private int product_fac_id;//制造商ID
    private String product_fac_name;
    private Timestamp produce_time;//制造时间
    private Timestamp validity;//有效期
    private int purchase_num;//进货数
    private double purchase_value;//进价
    private double purchase_value_sum;//总价格
    private Timestamp purchase_time;//进货时间
    private int value;
    private int v_date;

    public int getV_date() {
        return v_date;
    }

    public void setV_date(int v_date) {
        this.v_date = v_date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDrug_name() {
        return drug_name;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

    public String getProduct_fac_name() {
        return product_fac_name;
    }

    public void setProduct_fac_name(String product_fac_name) {
        this.product_fac_name = product_fac_name;
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

    public String getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(String batch_id) {
        this.batch_id = batch_id;
    }

    public int getProduct_fac_id() {
        return product_fac_id;
    }

    public void setProduct_fac_id(int product_fac_id) {
        this.product_fac_id = product_fac_id;
    }


    public int getPurchase_num() {
        return purchase_num;
    }

    public void setPurchase_num(int purchase_num) {
        this.purchase_num = purchase_num;
    }

    public double getPurchase_value() {
        return purchase_value;
    }

    public void setPurchase_value(double purchase_value) {
        this.purchase_value = purchase_value;
    }

    public double getPurchase_value_sum() {
        return purchase_value_sum;
    }

    public void setPurchase_value_sum(double purchase_value_sum) {
        this.purchase_value_sum = purchase_value_sum;
    }

    public Timestamp getProduce_time() {
        return produce_time;
    }

    public void setProduce_time(Timestamp produce_time) {
        this.produce_time = produce_time;
    }

    public Timestamp getValidity() {
        return validity;
    }

    public void setValidity(Timestamp validity) {
        this.validity = validity;
    }

    public Timestamp getPurchase_time() {
        return purchase_time;
    }

    public void setPurchase_time(Timestamp purchase_time) {
        this.purchase_time = purchase_time;
    }
}
