package com.lxl.trade.common.protocol.coupon;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lixiaole
 * @date 2018/9/9
 * @Description
 */
public class QueryCouponRes {

    private String couponId;

    private BigDecimal couponPrice;

    private Integer userId;

    private String orderId;

    private String isUsed;

    private Date usedTime;

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
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

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    public Date getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
    }
}
