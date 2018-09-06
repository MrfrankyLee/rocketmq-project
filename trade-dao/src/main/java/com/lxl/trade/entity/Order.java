package com.lxl.trade.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Order implements Serializable {
    private String orderId;

    private Integer userId;

    private String orderStatus;

    private String payStatus;

    private String shippingStatus;

    private String address;

    private String consignee;

    private Integer goodsId;

    private Integer goodsNumber;

    private BigDecimal goodsPrice;

    private BigDecimal goodsAmount;

    private BigDecimal shippingFee;

    private BigDecimal orderAmount;

    private String couponId;

    private BigDecimal couponPaid;

    private BigDecimal moneyPaid;

    private BigDecimal payAmount;

    private Date addTime;

    private Date confirmTime;

    private Date payTime;

    private static final long serialVersionUID = 1L;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus == null ? null : shippingStatus.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

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

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(BigDecimal goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId == null ? null : couponId.trim();
    }

    public BigDecimal getCouponPaid() {
        return couponPaid;
    }

    public void setCouponPaid(BigDecimal couponPaid) {
        this.couponPaid = couponPaid;
    }

    public BigDecimal getMoneyPaid() {
        return moneyPaid;
    }

    public void setMoneyPaid(BigDecimal moneyPaid) {
        this.moneyPaid = moneyPaid;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", userId=").append(userId);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", payStatus=").append(payStatus);
        sb.append(", shippingStatus=").append(shippingStatus);
        sb.append(", address=").append(address);
        sb.append(", consignee=").append(consignee);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsNumber=").append(goodsNumber);
        sb.append(", goodsPrice=").append(goodsPrice);
        sb.append(", goodsAmount=").append(goodsAmount);
        sb.append(", shippingFee=").append(shippingFee);
        sb.append(", orderAmount=").append(orderAmount);
        sb.append(", couponId=").append(couponId);
        sb.append(", couponPaid=").append(couponPaid);
        sb.append(", moneyPaid=").append(moneyPaid);
        sb.append(", payAmount=").append(payAmount);
        sb.append(", addTime=").append(addTime);
        sb.append(", confirmTime=").append(confirmTime);
        sb.append(", payTime=").append(payTime);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Order other = (Order) that;
        return (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getOrderStatus() == null ? other.getOrderStatus() == null : this.getOrderStatus().equals(other.getOrderStatus()))
            && (this.getPayStatus() == null ? other.getPayStatus() == null : this.getPayStatus().equals(other.getPayStatus()))
            && (this.getShippingStatus() == null ? other.getShippingStatus() == null : this.getShippingStatus().equals(other.getShippingStatus()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getConsignee() == null ? other.getConsignee() == null : this.getConsignee().equals(other.getConsignee()))
            && (this.getGoodsId() == null ? other.getGoodsId() == null : this.getGoodsId().equals(other.getGoodsId()))
            && (this.getGoodsNumber() == null ? other.getGoodsNumber() == null : this.getGoodsNumber().equals(other.getGoodsNumber()))
            && (this.getGoodsPrice() == null ? other.getGoodsPrice() == null : this.getGoodsPrice().equals(other.getGoodsPrice()))
            && (this.getGoodsAmount() == null ? other.getGoodsAmount() == null : this.getGoodsAmount().equals(other.getGoodsAmount()))
            && (this.getShippingFee() == null ? other.getShippingFee() == null : this.getShippingFee().equals(other.getShippingFee()))
            && (this.getOrderAmount() == null ? other.getOrderAmount() == null : this.getOrderAmount().equals(other.getOrderAmount()))
            && (this.getCouponId() == null ? other.getCouponId() == null : this.getCouponId().equals(other.getCouponId()))
            && (this.getCouponPaid() == null ? other.getCouponPaid() == null : this.getCouponPaid().equals(other.getCouponPaid()))
            && (this.getMoneyPaid() == null ? other.getMoneyPaid() == null : this.getMoneyPaid().equals(other.getMoneyPaid()))
            && (this.getPayAmount() == null ? other.getPayAmount() == null : this.getPayAmount().equals(other.getPayAmount()))
            && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()))
            && (this.getConfirmTime() == null ? other.getConfirmTime() == null : this.getConfirmTime().equals(other.getConfirmTime()))
            && (this.getPayTime() == null ? other.getPayTime() == null : this.getPayTime().equals(other.getPayTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getOrderStatus() == null) ? 0 : getOrderStatus().hashCode());
        result = prime * result + ((getPayStatus() == null) ? 0 : getPayStatus().hashCode());
        result = prime * result + ((getShippingStatus() == null) ? 0 : getShippingStatus().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getConsignee() == null) ? 0 : getConsignee().hashCode());
        result = prime * result + ((getGoodsId() == null) ? 0 : getGoodsId().hashCode());
        result = prime * result + ((getGoodsNumber() == null) ? 0 : getGoodsNumber().hashCode());
        result = prime * result + ((getGoodsPrice() == null) ? 0 : getGoodsPrice().hashCode());
        result = prime * result + ((getGoodsAmount() == null) ? 0 : getGoodsAmount().hashCode());
        result = prime * result + ((getShippingFee() == null) ? 0 : getShippingFee().hashCode());
        result = prime * result + ((getOrderAmount() == null) ? 0 : getOrderAmount().hashCode());
        result = prime * result + ((getCouponId() == null) ? 0 : getCouponId().hashCode());
        result = prime * result + ((getCouponPaid() == null) ? 0 : getCouponPaid().hashCode());
        result = prime * result + ((getMoneyPaid() == null) ? 0 : getMoneyPaid().hashCode());
        result = prime * result + ((getPayAmount() == null) ? 0 : getPayAmount().hashCode());
        result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
        result = prime * result + ((getConfirmTime() == null) ? 0 : getConfirmTime().hashCode());
        result = prime * result + ((getPayTime() == null) ? 0 : getPayTime().hashCode());
        return result;
    }
}