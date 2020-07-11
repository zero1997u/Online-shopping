package com.cskaoyan.project1.dao;

import com.cskaoyan.project1.model.*;
import com.cskaoyan.project1.model.vo.*;
import com.cskaoyan.project1.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.management.Query;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDaoImpl implements GoodsDao {
    @Override
    public List<Type> getType() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Type> typeList = null;
        try {
            typeList = runner.query("select * from type",new BeanListHandler<Type>(Type.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeList;
    }

    @Override
    public List<TypeGoodsVO> getGoodsByType(String typeId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<TypeGoodsVO> typeGoodsVOList = null;
        try {
            typeGoodsVOList = runner.query("select id,img,name,price,typeId,stockNum from goods where typeId = ?",
                    new BeanListHandler<TypeGoodsVO>(TypeGoodsVO.class),
                    typeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeGoodsVOList;
    }

    @Override
    public void addGoods(Goods goods) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("insert into goods values (null,?,?,?,?,?,?)",
                    goods.getImg(),
                    goods.getName(),
                    goods.getPrice(),
                    goods.getTypeId(),
                    goods.getStockNum(),
                    goods.getDesc());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int lastInsertId() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        BigInteger query = null;
        try {
            query = runner.query("select last_insert_id()", new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query.intValue();
    }

    @Override
    public void addSpecs(List<Spec> specs) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        for (Spec spec : specs) {
            try {
                runner.update("insert into spec values(null,?,?,?,?)"
                ,spec.getSpecName(),
                        spec.getStockNum(),
                        spec.getUnitPrice(),
                        spec.getGoodsId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Message> noReplyMsg() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<MessageVO> messageVOList = null;
        try {
            messageVOList = runner.query("select * from message where state = 1",new BeanListHandler<MessageVO>(MessageVO.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Message> messageList = new ArrayList<>();
        Message message;
        for(int i=0;i<messageVOList.size();i++)
        {
            message = new Message();
            message.setId(messageVOList.get(i).getId());
            message.setUserId(messageVOList.get(i).getUserId());
            message.setGoodsId(messageVOList.get(i).getGoodsId());
            message.setContent(messageVOList.get(i).getContent());
            message.setState(messageVOList.get(i).getState());
            message.setCreatetime(messageVOList.get(i).getCreatetime());
            MessageGood messageGood = new MessageGood();
            messageGood.setName(messageVOList.get(i).getGoodsname());
            message.setGoods(messageGood);
            MessageUser messageUser = new MessageUser();
            messageUser.setName(messageVOList.get(i).getUsername());
            message.setUser(messageUser);
            messageList.add(message);
            System.out.println(messageList.size());
        }
        return messageList;
    }

    @Override
    public List<Message> replicedMsg() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<MessageVO> messageVOList = null;
        try {
            messageVOList = runner.query("select * from message where state = 0",new BeanListHandler<MessageVO>(MessageVO.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Message> messageList = new ArrayList<>();
        Message message;
        for(int i=0;i<messageVOList.size();i++)
        {
            message = new Message();
            message.setId(messageVOList.get(i).getId());
            message.setUserId(messageVOList.get(i).getUserId());
            message.setGoodsId(messageVOList.get(i).getGoodsId());
            message.setContent(messageVOList.get(i).getContent());
            message.setState(messageVOList.get(i).getState());
            message.setCreatetime(messageVOList.get(i).getCreatetime());
            MessageGood messageGood = new MessageGood();
            messageGood.setName(messageVOList.get(i).getGoodsname());
            message.setGoods(messageGood);
            MessageUser messageUser = new MessageUser();
            messageUser.setName(messageVOList.get(i).getUsername());
            message.setUser(messageUser);
            message.setReplyContent(messageVOList.get(i).getReplyContent());
            messageList.add(message);
            System.out.println(messageList.size());

        }
        return messageList;
    }

    @Override
    public void reply(Message message) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("update message set state = 0,replyContent = ? where id = ?",message.getReplyContent(),message.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addType(Type type) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("insert into type values(null,?)",type.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteType(Integer id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("delete from type where id = ?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public GoodChangeVO getGoodsInfo(Integer id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<SpecVO> specs = null;
        try {
            specs = runner.query("select id,specName,stockNum,unitPrice from spec where goodsId = ?",new BeanListHandler<SpecVO>(SpecVO.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        GoodsVO goods = new GoodsVO();
        try {
            goods = runner.query("select id,img,name,'desc',typeId from goods where id = ?",new BeanHandler<>(GoodsVO.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        GoodChangeVO goodChangeVO = new GoodChangeVO();
        goodChangeVO.setSpecs(specs);
        goodChangeVO.setGoods(goods);
        return goodChangeVO;
    }
}
