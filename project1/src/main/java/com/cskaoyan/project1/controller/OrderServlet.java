package com.cskaoyan.project1.controller;

import com.cskaoyan.project1.model.Result;
import com.cskaoyan.project1.model.bo.PageOrderBO;
import com.cskaoyan.project1.model.vo.LoginVO;
import com.cskaoyan.project1.model.vo.PageOrdersVO;
import com.cskaoyan.project1.service.OrderService;
import com.cskaoyan.project1.service.OrderServiceImpl;
import com.cskaoyan.project1.utils.HttpUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/admin/order/*")
public class OrderServlet extends HttpServlet {

    private Gson gson = new Gson();

    private OrderService orderService = new OrderServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/order/","" );
        if("ordersByPage".equals(action)){
            ordersByPage(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/order/","" );

    }
    /**
     * @Description :根据条件分页显示订单
     1.获取请求参数
     2.业务逻辑处理
     3.响应
     @param request
	 * @param response
     * @Return : void
     */
    private void ordersByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        PageOrderBO orderBO = gson.fromJson(requestBody,PageOrderBO.class);
        PageOrdersVO  orders = orderService.ordersByPage(orderBO);
        response.getWriter().println(gson.toJson(Result.ok(orders)));
    }
}
