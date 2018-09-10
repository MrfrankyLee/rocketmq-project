package com.lxl.trade.order.api;

import com.lxl.trade.common.api.OrderApi;
import com.lxl.trade.common.protocol.order.ConfirmOrderReq;
import com.lxl.trade.common.protocol.order.ConfirmOrderRes;
import com.lxl.trade.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lixiaole
 * @date 2018/9/9
 * @Description
 */
@Controller
public class OrderApiImpl implements OrderApi {
    @Autowired
    private OrderService orderService;
    @RequestMapping(value = "/confirmOrder" ,method = RequestMethod.POST)
    @ResponseBody
    public ConfirmOrderRes confirmOrder(@RequestBody ConfirmOrderReq confirmOrderReq) {
        ConfirmOrderRes confirmOrderRes = this.orderService.confirmOrder(confirmOrderReq);
        return confirmOrderRes;
    }
}
