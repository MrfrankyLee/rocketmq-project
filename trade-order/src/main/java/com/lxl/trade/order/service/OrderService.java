package com.lxl.trade.order.service;

import com.lxl.trade.common.protocol.order.ConfirmOrderReq;
import com.lxl.trade.common.protocol.order.ConfirmOrderRes;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author lixiaole
 * @date 2018/9/9
 * @Description
 */
public interface OrderService {
    public ConfirmOrderRes confirmOrder(@RequestBody ConfirmOrderReq confirmOrderReq);
}
