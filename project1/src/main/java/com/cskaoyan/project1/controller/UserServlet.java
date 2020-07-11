package com.cskaoyan.project1.controller;

import com.cskaoyan.project1.model.Result;
import com.cskaoyan.project1.model.User;
import com.cskaoyan.project1.model.bo.UserSearchBO;
import com.cskaoyan.project1.service.UserService;
import com.cskaoyan.project1.service.UserServiceImpl;
import com.cskaoyan.project1.utils.HttpUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/admin/user/*")
public class UserServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/user/","" );


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/user/","" );
        if("allUser".equals(action)){
            allUser(request,response);
        }else if("searchUser".equals(action)){
            searchUser(request,response);
        }else if("deleteUser".equals(action)){
            deleteUser(request,response);
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        userService.deleteUser(id);
        response.getWriter().println(gson.toJson(Result.ok(0)));
    }

    private void searchUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String word = request.getParameter("word");
        List<User> user = userService.searchUser(word);
        Result result = new Result();
        result.setCode(0);
        result.setData(user);
        response.getWriter().println(gson.toJson(result));
    }

    private void allUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> userList = userService.allUser();
        Result result = new Result();
        result.setCode(0);
        result.setData(userList);
        response.getWriter().println(gson.toJson(result));
    }
}
