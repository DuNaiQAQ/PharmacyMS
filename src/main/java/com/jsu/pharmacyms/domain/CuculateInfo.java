package com.jsu.pharmacyms.domain;

import java.util.List;

public class CuculateInfo {
    private List<String> label;
    private List<Integer> values;
    private List<Integer> out_values;

    public List<String> getLabel() {
        return label;
    }

    public void setLabel(List<String> label) {
        this.label = label;
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }

    public List<Integer> getOut_values() {
        return out_values;
    }

    public void setOut_values(List<Integer> out_values) {
        this.out_values = out_values;
    }
}
