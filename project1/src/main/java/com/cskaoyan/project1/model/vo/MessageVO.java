package com.cskaoyan.project1.model.vo;

import com.cskaoyan.project1.model.Goods;
import com.cskaoyan.project1.model.User;

public class MessageVO {
    private Integer id;
    private Integer userId;
    private Integer goodsId;
    private String content;
    private String replyContent;
    private Integer state;
    private String createtime;
    private String goodsname;
    private String username;

    public Integer getId() {
        return id;
    }

    public MessageVO() {
    }

    public MessageVO(Integer id, Integer userId, Integer goodsId, String content, Integer state, String createtime, String goodsname, String username) {
        this.id = id;
        this.userId = userId;
        this.goodsId = goodsId;
        this.content = content;
        this.state = state;
        this.createtime = createtime;
        this.goodsname = goodsname;
        this.username = username;
    }

    public MessageVO(Integer id, Integer userId, Integer goodsId, String content, String replyContent, Integer state, String createtime, String goodsname, String username) {
        this.id = id;
        this.userId = userId;
        this.goodsId = goodsId;
        this.content = content;
        this.replyContent = replyContent;
        this.state = state;
        this.createtime = createtime;
        this.goodsname = goodsname;
        this.username = username;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
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

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
