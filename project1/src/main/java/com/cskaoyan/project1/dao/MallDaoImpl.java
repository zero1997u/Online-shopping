package com.cskaoyan.project1.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.cskaoyan.project1.model.vo.TypeGoodsVO;
import com.cskaoyan.project1.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class MallDaoImpl implements MallDao {
    @Override
    public List<TypeGoodsVO> getGoodsByType(String id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<TypeGoodsVO> typeGoodsVOList = null;
        try {
            typeGoodsVOList = runner.query(
                    "select * from goods ",new BeanListHandler<TypeGoodsVO>(TypeGoodsVO.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return typeGoodsVOList;
    }
}
