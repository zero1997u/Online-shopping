package com.cskaoyan.project1.model.vo;

/**
 * 管理员登录成功返回vo
 */
public class AdminLoginVo {
    private String token;
    private String name;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
