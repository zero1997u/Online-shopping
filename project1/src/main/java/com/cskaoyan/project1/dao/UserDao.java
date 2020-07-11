package com.cskaoyan.project1.dao;

import com.cskaoyan.project1.model.User;

import java.util.List;

public interface UserDao {
    List<User> allUser();

    List<User> searchUser(String s);

    void deleteUser(String id);
}
