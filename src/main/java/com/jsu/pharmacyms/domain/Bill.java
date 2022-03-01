package com.jsu.pharmacyms.domain;

import java.sql.Timestamp;

public class Bill {
    private int id;
    private String bill_info;
    private double value;
    private Timestamp bill_time;
    private int bill_type;

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
}
