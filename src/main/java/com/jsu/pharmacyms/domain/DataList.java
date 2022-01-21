package com.jsu.pharmacyms.domain;

import java.util.List;

/**
 * 服务器返回数据列表
 * */
public class DataList {
    private int itemNums;//列表元素数量
    private List<?> items;//列表元素

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
}
