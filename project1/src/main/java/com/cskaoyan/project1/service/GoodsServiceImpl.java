package com.cskaoyan.project1.service;

import com.cskaoyan.project1.dao.GoodsDao;
import com.cskaoyan.project1.dao.GoodsDaoImpl;
import com.cskaoyan.project1.model.Goods;
import com.cskaoyan.project1.model.Message;
import com.cskaoyan.project1.model.Spec;
import com.cskaoyan.project1.model.Type;
import com.cskaoyan.project1.model.bo.GoodsBO;
import com.cskaoyan.project1.model.bo.ReplyBO;
import com.cskaoyan.project1.model.bo.SpecBO;
import com.cskaoyan.project1.model.bo.TypeBO;
import com.cskaoyan.project1.model.vo.GoodChangeVO;
import com.cskaoyan.project1.model.vo.TypeGoodsVO;

import java.util.ArrayList;
import java.util.List;

public class GoodsServiceImpl implements GoodsService {
    private GoodsDao goodsDao = new GoodsDaoImpl();
    @Override
    public List<Type> getType() {
        return goodsDao.getType();
    }

    @Override
    public List<TypeGoodsVO> getGoodsByType(String typeId) {
        return goodsDao.getGoodsByType(typeId);
    }

    /**
     * @Description :新增商品表
     * price，stocknum需要通过speclist运算
     * 1.保存数据到商品表
     * 2.拿到商品表刚刚插入的商品Id
     * 将该id以及spec数据保存到spec规格表
       @param goodsBO
     * @Return : void
     */
    @Override
    public void addGoods(GoodsBO goodsBO) {
        List<SpecBO> specList = goodsBO.getSpecList();
        double price = specList.get(0).getUnitPrice();
        int stockNum = specList.get(0).getStockNum();
        for(int i = 1;i<specList.size();i++){
            if(price>specList.get(i).getUnitPrice()){
                price = specList.get(i).getUnitPrice();
            }
            if(stockNum<specList.get(i).getStockNum()){
                stockNum = specList.get(i).getStockNum();
            }
        }
        Goods goods = new Goods(null,goodsBO.getImg(),goodsBO.getName(),price,goodsBO.getTypeId(),stockNum,goodsBO.getDesc());
        goodsDao.addGoods(goods);
        //该id就是goods表新增的商品id
        int id = goodsDao.lastInsertId();
        List<Spec> specs = new ArrayList<>();
        for (SpecBO specBO : specList) {
            Spec spec = new Spec(null, specBO.getSpecName(), specBO.getStockNum(), specBO.getUnitPrice(), id);
            specs.add(spec);
        }
        goodsDao.addSpecs(specs);
    }

    @Override
    public List<Message> noReplyMsg() {
        return goodsDao.noReplyMsg();
    }

    @Override
    public List<Message> replicedMsg() {
        return goodsDao.replicedMsg();
    }

    @Override
    public void reply(ReplyBO replyBO) {
        Message message = new Message();
        message.setId(replyBO.getId());
        message.setReplyContent(replyBO.getContent());
        goodsDao.reply(message);
    }

    @Override
    public void addType(TypeBO typeBO) {
        Type type = new Type();
        type.setName(typeBO.getName());
        goodsDao.addType(type);
    }

    @Override
    public void deleteType(Integer id) {
        goodsDao.deleteType(id);
    }

    @Override
    public GoodChangeVO getGoodsInfo(Integer id) {
        return goodsDao.getGoodsInfo(id);
    }
}
