package com.jsu.pharmacyms.domain;

public class PurchaseProject {
    private int id;
    private int drug_id;
    private String drug_name;
    private int purchase_num;
    private int is_purchase;

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

    public int getPurchase_num() {
        return purchase_num;
    }

    public void setPurchase_num(int purchase_num) {
        this.purchase_num = purchase_num;
    }

    public int getIs_purchase() {
        return is_purchase;
    }

    public void setIs_purchase(int is_purchase) {
        this.is_purchase = is_purchase;
    }
}
