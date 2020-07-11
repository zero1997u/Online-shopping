package com.cskaoyan.project1.model.vo;

import java.util.List;

/**
 * @Description :用于后台管理系统显示分页订单
   @param null
 * @Return :
 */
public class PageOrdersVO {
    private Integer total;
    private List<PageOrderInfoVO> orders;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<PageOrderInfoVO> getOrders() {
        return orders;
    }

    public void setOrders(List<PageOrderInfoVO> orders) {
        this.orders = orders;
    }
}
