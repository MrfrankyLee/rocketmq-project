package com.lxl.trade.common.api;

import com.lxl.trade.common.protocol.order.ConfirmOrderReq;
import com.lxl.trade.common.protocol.order.ConfirmOrderRes;

/**
 * @author lixiaole
 * @date 2018/9/8
 * @Description
 */
public interface OrderApi {
    public ConfirmOrderRes confirmOrder(ConfirmOrderReq confirmOrderReq);
}
