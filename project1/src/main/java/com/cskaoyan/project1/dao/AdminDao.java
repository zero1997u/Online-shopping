package com.cskaoyan.project1.dao;

import com.cskaoyan.project1.model.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface AdminDao {
   Admin login(Admin admin);

   List<Admin> allAdmins();

   int addAdmin(String s);

   void deleteAdmins(int id);

   void updateAdmin(String requestBody);

   Admin getAdminsInfo(int id);
}
