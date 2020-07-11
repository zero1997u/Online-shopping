package com.cskaoyan.project1.model;

import javax.xml.crypto.Data;

public class Message {
    private Integer id;
    private Integer userId;
    private Integer goodsId;
    private String content;
    private String replyContent;
    private Integer state;
    private String createtime;
    private MessageGood goods;
    private MessageUser user;


    public Message() {
    }

    public Message(Integer id, Integer userId, Integer goodsId, String content, Integer state, String createtime, MessageGood goods, MessageUser user) {
        this.id = id;
        this.userId = userId;
        this.goodsId = goodsId;
        this.content = content;
        this.state = state;
        this.createtime = createtime;
        this.goods = goods;
        this.user = user;
    }

    public Message(Integer id, Integer userId, Integer goodsId, String content, String replyContent, Integer state, String createtime, MessageGood goods, MessageUser user) {
        this.id = id;
        this.userId = userId;
        this.goodsId = goodsId;
        this.content = content;
        this.replyContent = replyContent;
        this.state = state;
        this.createtime = createtime;
        this.goods = goods;
        this.user = user;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public MessageGood getGoods() {
        return goods;
    }

    public void setGoods(MessageGood goods) {
        this.goods = goods;
    }

    public MessageUser getUser() {
        return user;
    }

    public void setUser(MessageUser user) {
        this.user = user;
    }
}
