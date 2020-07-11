package com.cskaoyan.project1.service;

import com.cskaoyan.project1.model.User;
import com.cskaoyan.project1.model.bo.UserSearchBO;

import java.util.List;

public interface UserService {
    List<User> allUser();

    List<User> searchUser(String s);

    void deleteUser(String id);
}
