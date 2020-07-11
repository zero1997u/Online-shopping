package com.cskaoyan.project1.model;

public class MessageGood {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MessageGood{" +
                "name='" + name + '\'' +
                '}';
    }
}
