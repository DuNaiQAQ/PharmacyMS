package com.jsu.pharmacyms.domain;

public class DashbordInfo {
    private int user_num;
    private int drug_num;
    private int stock_num;

    public int getStock_num() {
        return stock_num;
    }

    public void setStock_num(int stock_num) {
        this.stock_num = stock_num;
    }

    public int getUser_num() {
        return user_num;
    }

    public void setUser_num(int user_num) {
        this.user_num = user_num;
    }

    public int getDrug_num() {
        return drug_num;
    }

    public void setDrug_num(int drug_num) {
        this.drug_num = drug_num;
    }
}
