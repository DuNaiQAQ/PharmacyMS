package com.jsu.pharmacyms.domain;

import java.util.List;

/**
 * 服务器返回数据列表
 * */
public class DataList {
    private int itemNums;//列表元素数量
    private List<?> items;//列表元素
    private double value_sum;

    public int getItemNums() {
        return itemNums;
    }

    public void setItemNums(int itemNums) {
        this.itemNums = itemNums;
    }

    public List<?> getItems() {
        return items;
    }

    public void setItems(List<?> items) {
        this.items = items;
    }

    public double getValue_sum() {
        return value_sum;
    }

    public void setValue_sum(double value_sum) {
        this.value_sum = value_sum;
    }
}
