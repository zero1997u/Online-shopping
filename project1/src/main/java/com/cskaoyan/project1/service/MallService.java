package com.cskaoyan.project1.service;

import com.cskaoyan.project1.model.vo.TypeGoodsVO;

import java.util.List;

public interface MallService {
    List<TypeGoodsVO> getGoodsByType(String id);
}
