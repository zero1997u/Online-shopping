package com.cskaoyan.project1.model.vo;

import java.util.List;

public class GoodChangeVO {
    private List<SpecVO> specs;
    private GoodsVO goods;

    public List<SpecVO> getSpecs() {
        return specs;
    }

    public void setSpecs(List<SpecVO> specs) {
        this.specs = specs;
    }

    public GoodsVO getGoods() {
        return goods;
    }

    public void setGoods(GoodsVO goods) {
        this.goods = goods;
    }
}
