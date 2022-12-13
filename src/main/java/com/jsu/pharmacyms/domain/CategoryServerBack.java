package com.jsu.pharmacyms.domain;

import java.util.List;

public class CategoryServerBack {
    private String label;//主分类名称
    private List<CategoryInfo> option;//子分类

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<CategoryInfo> getOption() {
        return option;
    }

    public void setOption(List<CategoryInfo> option) {
        this.option = option;
    }
}
