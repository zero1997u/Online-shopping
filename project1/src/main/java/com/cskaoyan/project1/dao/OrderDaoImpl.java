package com.cskaoyan.project1.dao;

import com.cskaoyan.project1.model.bo.PageOrderBO;
import com.cskaoyan.project1.model.vo.PageOrderInfoVO;
import com.cskaoyan.project1.model.vo.PageOrdersVO;
import com.cskaoyan.project1.utils.DruidUtils;
import com.cskaoyan.project1.utils.StringUtils;
import javafx.beans.binding.ObjectBinding;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {

    @Override
    public List<PageOrderInfoVO> ordersByPage(PageOrderBO orderBO) {
        Map<String,Object> results = getDynamicSql(orderBO);
        String sql = (String)results.get("sql");
        List<Object> params = (List<Object>)results.get("params");
        String prefix_sql = "select id,userId,goodsDetailId,goods,spec,num as goodsNum,amount,stateId,nickname,name,address,phone ";
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
            params.add(orderBO.getPagesize());
            params.add((orderBO.getCurrentPage()-1)*orderBO.getPagesize());
        List<PageOrderInfoVO> orderInfoVOS = null;
        try {
            orderInfoVOS = runner.query(prefix_sql + sql
                    + " limit ? offset ?", new BeanListHandler<PageOrderInfoVO>(PageOrderInfoVO.class), params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderInfoVOS;
    }

    /**
     * @Description :select count(id) from orders where....
       @param orderBO
     * @Return : int
     */
    @Override
    public int getTotalCounts(PageOrderBO orderBO) {
        Map<String,Object> results = getDynamicSql(orderBO);
        String sql = (String)results.get("sql");
        List<Object> params = (List<Object>)results.get("params");
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        String prefix_sql = "select count(id) ";
        Long query = null;
        try {
            query = runner.query(prefix_sql+sql,new ScalarHandler<>(),params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query.intValue();
    }

    private Map<String, Object> getDynamicSql(PageOrderBO orderBO) {
        String base = " from orders where 1 = 1 ";
        List<Object> list = new ArrayList<>();
        if(orderBO.getState()!=-1){
            base = base+" and stateId = ?";
            list.add(orderBO.getState());
        }
        if(!StringUtils.isEmpty(orderBO.getMoneyLimit1())){
            base = base+" and amount <= ？";
            list.add(Double.parseDouble(orderBO.getMoneyLimit1()));
        }
        if(!StringUtils.isEmpty(orderBO.getMoneyLimit2())){
            base = base+" and amount >= ？";
            list.add(Double.parseDouble(orderBO.getMoneyLimit2()));
        }
        if(!StringUtils.isEmpty(orderBO.getGoods())){
            base = base +" and goods like ?";
            list.add("%"+orderBO.getGoods()+"%");
        }
        if(!StringUtils.isEmpty(orderBO.getAddress())){
            base = base +" and address like ?";
            list.add("%"+orderBO.getAddress()+"%");
        }
        if(!StringUtils.isEmpty(orderBO.getName())){
            base = base +" and name like ?";
            list.add("%"+orderBO.getName()+"%");
        }
        if(!StringUtils.isEmpty(orderBO.getId())){
            base = base +" and id = ?";
            list.add(Integer.parseInt(orderBO.getGoods()));
        }
        Map<String,Object> map = new HashMap<>();
        map.put("sql",base);
        map.put("params",list);
        return map;



    }
}
