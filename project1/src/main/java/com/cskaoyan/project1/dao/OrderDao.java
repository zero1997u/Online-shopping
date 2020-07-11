package com.cskaoyan.project1.dao;

import com.cskaoyan.project1.model.bo.PageOrderBO;
import com.cskaoyan.project1.model.vo.PageOrderInfoVO;
import com.cskaoyan.project1.model.vo.PageOrdersVO;

import java.util.List;

public interface OrderDao {
    List<PageOrderInfoVO> ordersByPage(PageOrderBO orderBO);

    int getTotalCounts(PageOrderBO orderBO);
}
