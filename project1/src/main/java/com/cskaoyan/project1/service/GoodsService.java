package com.cskaoyan.project1.service;

import com.cskaoyan.project1.model.Message;
import com.cskaoyan.project1.model.Type;
import com.cskaoyan.project1.model.bo.GoodsBO;
import com.cskaoyan.project1.model.bo.ReplyBO;
import com.cskaoyan.project1.model.bo.TypeBO;
import com.cskaoyan.project1.model.vo.GoodChangeVO;
import com.cskaoyan.project1.model.vo.TypeGoodsVO;

import java.util.List;

public interface GoodsService {
    List<Type> getType();

    List<TypeGoodsVO> getGoodsByType(String typeId);

    void addGoods(GoodsBO goodsBO);

    List<Message> noReplyMsg();

    List<Message> replicedMsg();

    void reply(ReplyBO replyBO);

    void addType(TypeBO typeBO);

    void deleteType(Integer id);

    GoodChangeVO getGoodsInfo(Integer id);
}
