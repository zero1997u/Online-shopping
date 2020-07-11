package com.cskaoyan.project1.service;

import com.cskaoyan.project1.dao.MallDao;
import com.cskaoyan.project1.dao.MallDaoImpl;
import com.cskaoyan.project1.model.vo.TypeGoodsVO;

import java.util.List;

public class MallServiceImpl implements MallService {
    MallDao mallDao = new MallDaoImpl();

    @Override
    public List<TypeGoodsVO> getGoodsByType(String id) {
        return mallDao.getGoodsByType(id);
    }
}
