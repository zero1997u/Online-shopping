package com.cskaoyan.project1.controller;

import com.cskaoyan.project1.model.Admin;
import com.cskaoyan.project1.model.Result;
import com.cskaoyan.project1.model.bo.AdminLoginBO;
import com.cskaoyan.project1.model.bo.AdminPwdBO;
import com.cskaoyan.project1.model.bo.AdminSearchBO;
import com.cskaoyan.project1.model.vo.AdminLoginVo;
import com.cskaoyan.project1.service.AdminService;
import com.cskaoyan.project1.service.AdminServiceImpl;
import com.cskaoyan.project1.utils.HttpUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/admin/admin/*")
public class AdminServlet extends HttpServlet {

    private AdminService adminService = new AdminServiceImpl();
    Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //既然当前servlet可以处理登录，查询，删除所有admin操作
        //那么怎么知道当前请求是什么呢？
        //有什么api可以区分不同的url
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/admin/","" );
        if("login".equals(action)){
            login(request,response);
        }else if("addAdminss".equals((action))){
            addAdmins(request,response);
        }else if("updateAdminss".equals(action)){
            updateAdmins(request,response);
        }else if("getSearchAdmins".equals(action)){
            getSearchAdmins(request,response);
        }else if("changePwd".equals(action)){
            changePwd(request,response);
        }
    }

    private void changePwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        AdminPwdBO adminPwdBO = gson.fromJson(requestBody,AdminPwdBO.class);
        int res = adminService.changePwd(adminPwdBO);
        if(res == 1)
        response.getWriter().println(gson.toJson(Result.ok(0)));
        else{
            response.getWriter().println(gson.toJson(Result.error("修改失败")));
        }
    }

    /**
     * @Description :条件查询admin管理员的信息
       @param request
	 * @param response
     * @Return : void
     */
    private void getSearchAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        AdminSearchBO searchBO = gson.fromJson(requestBody, AdminSearchBO.class);
        List<Admin> admins = adminService.getSearchAdmins(searchBO);
        response.getWriter().println(gson.toJson(Result.ok(admins)));
    }

    private void updateAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
    //    System.out.println(requestBody);
        adminService.updateAdmins(requestBody);
        response.getWriter().println(gson.toJson(Result.ok(0)));
    }

    private void addAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
      //  System.out.println(requestBody);
        int res = adminService.addAdmin(requestBody);
        response.getWriter().println(gson.toJson(Result.ok(0)));

    }
    //管理员登录的具体业务逻辑
    //浏览器向8084发送了用户名密码
    //2.查询数据库，检验
    //3.根据结果返回不同的响应
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //取出请求参数--取出请求体里面的数据
        //此时无法使用request.getParamater key=value
        //而这个是json字符串形式
        String requestBody = HttpUtils.getRequestBody(request);
        //System.out.println(requestBody);
        //json字符串-》java对象
        AdminLoginBO loginBO = gson.fromJson(requestBody,AdminLoginBO.class);
        //System.out.println(loginBO);
        adminService.login(loginBO);
        Admin login = adminService.login(loginBO);
        if(login!=null){
            request.getSession().setAttribute("admin",login);
            AdminLoginVo loginVo = new AdminLoginVo();
            loginVo.setToken(login.getNickname());
            loginVo.setName(login.getNickname());
            response.getWriter().println(gson.toJson(Result.ok(loginVo)));
        }else{
            response.getWriter().println(Result.error("用户名密码错误"));
        }

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/admin/","" );
        if("allAdmins".equals(action)){
            allAdmins(request,response);
        }else if(("deleteAdmins").equals(action)){
            String s = request.getParameter("id");
            int id  = Integer.valueOf(s);
            deleteAdmins(request,response,id);
        }else if("getAdminsInfo".equals(action)){
            String s = request.getParameter("id");
            int id = Integer.valueOf(s);
            System.out.println(id);
            getAdminsInfo(request,response,id);
        }
    }

    private void getAdminsInfo(HttpServletRequest request, HttpServletResponse response, int id) throws IOException {
        Admin admin = adminService.getAdminsInfo(id);
        response.getWriter().println(gson.toJson(Result.ok(admin)));
    }

    private void deleteAdmins(HttpServletRequest request, HttpServletResponse response, int id) throws IOException {
        adminService.deleteAdmins(id);
        response.getWriter().println(gson.toJson(Result.ok(0)));
    }


    /**
     * @Description :显示所有admin账户信息
     * 1.查询数据库，返回数据
     * 2.作出响应
       @param request
       @param response
     * @Return : void
     */
    private void allAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Admin> adminList = adminService.allAdmins();
        Result result = new Result();
        result.setCode(0);
        result.setData(adminList);
        response.getWriter().println(gson.toJson(result));
    }
}
