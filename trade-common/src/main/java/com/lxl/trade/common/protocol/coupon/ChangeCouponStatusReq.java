package com.lxl.trade.common.protocol.coupon;

/**
 * @author lixiaole
 * @date 2018/9/9
 * @Description
 */
public class ChangeCouponStatusReq {
    private String CouponId;

    private Integer userId;

    private String orderId;

    public String getCouponId() {
        return CouponId;
    }

    public void setCouponId(String couponId) {
        CouponId = couponId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
