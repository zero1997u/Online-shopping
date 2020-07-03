package com.cskaoyan.project1.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.cskaoyan.project1.model.Admin;
import com.cskaoyan.project1.utils.DruidUtils;
import com.cskaoyan.project1.utils.HttpUtils;
import com.google.gson.Gson;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    @Override
    public Admin login(Admin admin){
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Admin query = null;
        try {
            query = runner.query("select * from admin where email= ? and pwd = ?",
                    new BeanHandler<>(Admin.class),
                    admin.getEmail(),
                    admin.getPwd());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public List<Admin> allAdmins() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Admin> admins = null;
        //不一样
        try {
            admins = runner.query("select * from admin",new BeanListHandler<Admin>(Admin.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public int addAdmin(String s) {

        Admin admin = new Gson().fromJson(s,Admin.class);
        System.out.println(admin);
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        int row = 0;
        String[] params = {admin.getEmail(),admin.getPwd(),admin.getNickname()};
        try {

            row = runner.update("insert into admin(email,pwd,nickname) values(?,?,?)",params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;

    }

    @Override
    public void deleteAdmins(int id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("delete from admin where id = ?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAdmin(String requestBody) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Admin admin = new Gson().fromJson(requestBody,Admin.class);
        //System.out.println(admin);
        try {
            runner.update("update admin set email = ?,nickname = ?,pwd=? where id = ?",
                    admin.getEmail(),
                    admin.getNickname(),
                    admin.getPwd(),admin.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Admin getAdminsInfo(int id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Admin admin = null;
        try {
            admin = runner.query("select * from admin where id = ?",new BeanHandler<>(Admin.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }
}
