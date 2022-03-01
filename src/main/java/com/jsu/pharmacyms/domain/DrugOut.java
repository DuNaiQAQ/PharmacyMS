package com.jsu.pharmacyms.domain;

public class DrugOut {
    private int id;
    private int drug_id;
    private String drug_name;
    private int out_num;
    private double value;
    private double out_sum;

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
