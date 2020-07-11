package com.cskaoyan.project1.dao;

import com.cskaoyan.project1.model.vo.TypeGoodsVO;

import java.util.List;

public interface MallDao {
    List<TypeGoodsVO> getGoodsByType(String id);
}
