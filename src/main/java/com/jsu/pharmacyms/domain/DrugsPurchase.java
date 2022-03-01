package com.jsu.pharmacyms.domain;

import java.sql.Date;

/**
 * <p>药品进货信息</p>
 * <p>要注意的是药品进货一般是以批次为主</p>
 * */
public class DrugsPurchase {
    private int id;//进货单ID
    private int drug_id;//药品名称
    private String batch_id;//批次号
    private int product_fac_id;//制造商ID
    private Date produce_time;//制造时间
    private Date validity;//有效期
    private int purchase_num;//进货数
    private double purchase_value;//进价
    private double purchase_value_sum;//总价格
    private Date purchase_date;//进货时间

    public int getId() {
        return id;
    }

    public Date getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(Date purchase_date) {
        this.purchase_date = purchase_date;
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

    public Date getProduce_time() {
        return produce_time;
    }

    public void setProduce_time(Date produce_time) {
        this.produce_time = produce_time;
    }

    public Date getValidity() {
        return validity;
    }

    public void setValidity(Date validity) {
        this.validity = validity;
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
}
