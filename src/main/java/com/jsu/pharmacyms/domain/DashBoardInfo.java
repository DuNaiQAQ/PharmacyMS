package com.jsu.pharmacyms.domain;

public class DashBoardInfo {
    private int drug_num;
    private int user_num;

    public DashBoardInfo(int drug_num, int user_num) {
        this.drug_num = drug_num;
        this.user_num = user_num;
    }

    public DashBoardInfo() {
    }

    public int getDrug_num() {
        return drug_num;
    }

    public void setDrug_num(int drug_num) {
        this.drug_num = drug_num;
    }

    public int getUser_num() {
        return user_num;
    }

    public void setUser_num(int user_num) {
        this.user_num = user_num;
    }
}
