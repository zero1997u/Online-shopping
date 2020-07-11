package com.cskaoyan.project1.service;

import com.cskaoyan.project1.dao.AdminDao;
import com.cskaoyan.project1.dao.AdminDaoImpl;
import com.cskaoyan.project1.model.Admin;
import com.cskaoyan.project1.model.bo.AdminLoginBO;
import com.cskaoyan.project1.model.bo.AdminPwdBO;
import com.cskaoyan.project1.model.bo.AdminSearchBO;
import com.cskaoyan.project1.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    private AdminDao adminDao = new AdminDaoImpl();
    @Override
    public Admin login(AdminLoginBO loginBO){
        Admin admin = new Admin();
        admin.setEmail(loginBO.getEmail());
        admin.setPwd(loginBO.getPwd());

        return adminDao.login(admin);
    }

    @Override
    public List<Admin> allAdmins() {
        return adminDao.allAdmins();
    }

    @Override
    public int addAdmin(String s) {
        return adminDao.addAdmin(s);
    }

    @Override
    public void deleteAdmins(int id) {
        adminDao.deleteAdmins(id);
    }

    @Override
    public void updateAdmins(String requestBody) {
        adminDao.updateAdmin(requestBody);
    }

    @Override
    public Admin getAdminsInfo(int id) {
        return adminDao.getAdminsInfo(id);
    }

    @Override
    public List<Admin> getSearchAdmins(AdminSearchBO searchBO) {
        Admin admin = new Admin();
        admin.setEmail(searchBO.getEmail());
        admin.setNickname(searchBO.getNickname());
        return adminDao.getSearchAdmins(admin);
    }

    @Override
    public int changePwd(AdminPwdBO adminPwdBO) {
        Admin admin = new Admin();
        admin.setNickname(adminPwdBO.getAdminToken());
        admin.setPwd(adminPwdBO.getNewPwd());
        return adminDao.changePwd(admin);
    }

}
