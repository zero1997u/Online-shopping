package com.cskaoyan.project1.service;

import com.cskaoyan.project1.dao.UserDao;
import com.cskaoyan.project1.dao.UserDaoImpl;
import com.cskaoyan.project1.model.User;
import com.cskaoyan.project1.model.bo.UserSearchBO;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public List<User> allUser() {
        return userDao.allUser();
    }

    @Override
    public List<User> searchUser(String s) {
        return userDao.searchUser(s);
    }

    @Override
    public void deleteUser(String id) {
        userDao.deleteUser(id);
    }
}
