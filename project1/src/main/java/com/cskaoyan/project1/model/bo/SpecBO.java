package com.cskaoyan.project1.model.bo;

/**
 * @Description :新增商品规格BO
   @param null
 * @Return :
 */
public class SpecBO {
    private String specName;
    private Integer stockNum;
    private Double unitPrice;

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
