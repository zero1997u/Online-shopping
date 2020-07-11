package com.cskaoyan.project1.service;

import com.cskaoyan.project1.model.bo.PageOrderBO;
import com.cskaoyan.project1.model.vo.PageOrdersVO;

import java.util.List;

public interface OrderService {
    PageOrdersVO ordersByPage(PageOrderBO orderBO);
}
