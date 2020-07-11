package com.cskaoyan.project1.model;

public class MessageUser {
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "MessageUser{" +
                "name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }
}
