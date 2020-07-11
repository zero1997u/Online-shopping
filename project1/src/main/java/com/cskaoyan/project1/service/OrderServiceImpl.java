package com.cskaoyan.project1.service;

import com.cskaoyan.project1.dao.OrderDao;
import com.cskaoyan.project1.dao.OrderDaoImpl;
import com.cskaoyan.project1.model.bo.PageOrderBO;
import com.cskaoyan.project1.model.vo.PageOrderInfoVO;
import com.cskaoyan.project1.model.vo.PageOrdersVO;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();

    /**
     * @Description :
     * 根据传入的不同参数，执行不同的查询，返回对应的一个结果
       @param orderBO
     * @Return :
     */
    @Override
    public PageOrdersVO ordersByPage(PageOrderBO orderBO) {
        int totalCounts = orderDao.getTotalCounts(orderBO);
        //查询当前分页的结果page1  =》 显示1-5数据
        List<PageOrderInfoVO> orderInfoVOS = orderDao.ordersByPage(orderBO);
        PageOrdersVO pageOrdersVO = new PageOrdersVO();
        pageOrdersVO.setTotal(totalCounts);
        pageOrdersVO.setOrders(orderInfoVOS);
        return pageOrdersVO;
    }
}
