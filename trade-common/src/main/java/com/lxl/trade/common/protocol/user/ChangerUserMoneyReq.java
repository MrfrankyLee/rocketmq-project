package com.lxl.trade.common.protocol.user;

import java.math.BigDecimal;

/**
 * @author lixiaole
 * @date 2018/9/9
 * @Description
 */
public class ChangerUserMoneyReq {
    private Integer userId;
    private BigDecimal userMoney;
    private String moneyLogType;
    private String OrderId;

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(BigDecimal userMoney) {
        this.userMoney = userMoney;
    }

    public String getMoneyLogType() {
        return moneyLogType;
    }

    public void setMoneyLogType(String moneyLogType) {
        this.moneyLogType = moneyLogType;
    }
}
