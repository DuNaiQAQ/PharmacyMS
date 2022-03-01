package com.jsu.pharmacyms.domain;

public class Factory {
    private int id;//工厂ID
    private String name;//制造商名字
    private String about;//制造商详情
    private String logo;//Logo地址

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
