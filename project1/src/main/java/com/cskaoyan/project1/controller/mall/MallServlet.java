package com.cskaoyan.project1.controller.mall;

import com.cskaoyan.project1.model.Result;
import com.cskaoyan.project1.model.vo.TypeGoodsVO;
import com.cskaoyan.project1.service.MallService;
import com.cskaoyan.project1.service.MallServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/mall/goods/*")
public class MallServlet extends HttpServlet {
    MallService mallService = new MallServiceImpl();
    Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/goods/","");
        if("getGoodsByType".equals(action)){
            getGoodsByType(request,response);
        }else if("getGoodsMsg".equals(action)){
            getGoodsInfo(request,response);
        }
    }

    private void getGoodsInfo(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");


    }

    private void getGoodsByType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String typeId = request.getParameter("typeId");
        List<TypeGoodsVO> typeGoodsVOList = mallService.getGoodsByType(typeId);
        Result result = new Result();
        result.setCode(0);
        result.setData(typeGoodsVOList);
        response.getWriter().println(gson.toJson(result));
    }
}
