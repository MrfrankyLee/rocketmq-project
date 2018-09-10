package com.lxl.trade.common.protocol.goods;

/**
 * @author lixiaole
 * @date 2018/9/9
 * @Description
 */
public class ReduceGoodsNumberReq {
    private Integer goodsId;
    private Integer goodsNumber;
    private String OrderId;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }
}
