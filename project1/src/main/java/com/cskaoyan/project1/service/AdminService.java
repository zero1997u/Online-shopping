package com.cskaoyan.project1.service;

import com.cskaoyan.project1.model.Admin;
import com.cskaoyan.project1.model.bo.AdminLoginBO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface AdminService {

    Admin login(AdminLoginBO loginBO);

    List<Admin> allAdmins();

    int addAdmin(String s);

    void deleteAdmins(int id);

    void updateAdmins(String requestBody);

    Admin getAdminsInfo(int id);
}
