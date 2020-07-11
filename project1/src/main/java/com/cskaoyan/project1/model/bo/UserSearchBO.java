package com.cskaoyan.project1.model.bo;

public class UserSearchBO {
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public UserSearchBO() {
    }

    public UserSearchBO(String word) {
        this.word = word;
    }
}
