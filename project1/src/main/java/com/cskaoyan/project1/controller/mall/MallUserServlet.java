package com.cskaoyan.project1.controller.mall;

import com.cskaoyan.project1.model.Result;
import com.cskaoyan.project1.model.bo.MallLoginBO;
import com.cskaoyan.project1.model.bo.MallLoginBO2;
import com.cskaoyan.project1.model.vo.LoginVO;
import com.cskaoyan.project1.service.MallUserService;
import com.cskaoyan.project1.service.MallUserServiceImpl;
import com.cskaoyan.project1.utils.HttpUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/mall/user/*")
public class MallUserServlet extends HttpServlet {

    MallUserService mallUserService = new MallUserServiceImpl();
    Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/user/","");
        if("signup".equals(action)){
            signup(request,response);
        }else if("login".equals(action)){
            login(request,response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        MallLoginBO2 mallLoginBO2 = gson.fromJson(requestBody,MallLoginBO2.class);
        LoginVO res = mallUserService.login(mallLoginBO2);
        if(res != null){
//            Result result = new Result();
//            result.setCode(0);
//            result.setData(res);
            response.getWriter().println(gson.toJson(Result.ok(res)));
        }
    }

    private void signup(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        MallLoginBO mallLoginBO = gson.fromJson(requestBody,MallLoginBO.class);
        mallUserService.signup(mallLoginBO);
        response.getWriter().println(gson.toJson(Result.ok(0)));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
