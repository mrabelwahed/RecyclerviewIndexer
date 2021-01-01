package com.ramadan.indexer;

public class Data {

    private String index;
    private String name;
    private String age;

    public Data() {
    }

    public Data(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setName(String name) {
        this.name = name;
    }
}