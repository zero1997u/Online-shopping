package com.cskaoyan.project1.dao;

import com.cskaoyan.project1.model.Admin;
import com.cskaoyan.project1.model.User;
import com.cskaoyan.project1.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> allUser() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<User> users = null;
        //不一样
        try {
            users = runner.query("select * from user",new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<User> searchUser(String s) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<User> userList = null;
        try {
            userList = runner.query("select * from user where nickname like ?",new BeanListHandler<User>(User.class),
                    "%"+s+"%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void deleteUser(String id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("delete from user where id = ?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
