package com.lxl.trade.common.protocol.goods;

import com.lxl.trade.common.protocol.BaseRes;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lixiaole
 * @date 2018/9/9
 * @Description
 */
public class QueryGoodsRes extends BaseRes {
    private Integer goodsId;

    private String goodsName;

    private Integer goodsNumber;

    private BigDecimal goodsPrice;

    private String goodsDesc;

    private Date addTime;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
