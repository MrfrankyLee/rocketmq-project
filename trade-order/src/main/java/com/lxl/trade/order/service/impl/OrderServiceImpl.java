package com.lxl.trade.order.service.impl;

import com.lxl.trade.common.Constants.TradeEnums;
import com.lxl.trade.common.api.GoodsApi;
import com.lxl.trade.common.exception.OrderException;
import com.lxl.trade.common.protocol.goods.QueryGoodsReq;
import com.lxl.trade.common.protocol.goods.QueryGoodsRes;
import com.lxl.trade.common.protocol.order.ConfirmOrderReq;
import com.lxl.trade.common.protocol.order.ConfirmOrderRes;
import com.lxl.trade.common.util.IDgenerator;
import com.lxl.trade.entity.Order;
import com.lxl.trade.mapper.OrderMapper;
import com.lxl.trade.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author lixiaole
 * @date 2018/9/9
 * @Description
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private GoodsApi goodsApi;

    @Autowired
    private OrderMapper orderMapper;

    public ConfirmOrderRes confirmOrder(ConfirmOrderReq confirmOrderReq) {
        ConfirmOrderRes confirmOrderRes = new ConfirmOrderRes();
        confirmOrderRes.setRestCode(TradeEnums.RestEnum.SUCCESS.getCode());
        try {
            QueryGoodsReq queryGoodsReq = new QueryGoodsReq();
            queryGoodsReq.setGoodsId(confirmOrderReq.getGoodsId());
            QueryGoodsRes queryGoodsRes = goodsApi.queryGoods(queryGoodsReq);
            // 1 参数校验
            checkConfirmOrderReq(confirmOrderReq,queryGoodsRes);
            // 2创建不可见订单
            saveNoConfirmOrder(confirmOrderReq);
            // 3调用远程服务,扣优惠券 扣库存 扣余额  如果调用成功->更改状态可见 失败->发送mq消息,取消订单
        } catch (Exception e) {
            confirmOrderRes.setRestCode(TradeEnums.RestEnum.FAIL.getCode());
            confirmOrderRes.setRestDesc(e.getMessage());
        }

        return confirmOrderRes;
    }

    private void saveNoConfirmOrder(ConfirmOrderReq confirmOrderReq) throws Exception {
        Order order = new Order();
        order.setOrderId(IDgenerator.generatorUUID());
        order.setAddress(confirmOrderReq.getAddress());
        order.setUserId(confirmOrderReq.getUserId());

        int result = orderMapper.insert(order);
        if(result != 1){
            throw new Exception("保存不可见订单失败");
        }
    }

    private void checkConfirmOrderReq(ConfirmOrderReq confirmOrderReq ,QueryGoodsRes queryGoodsRes) {
        if(confirmOrderReq == null){
            throw new OrderException("下单信息不能为空");
        }

        if(confirmOrderReq.getUserId() == null){
            throw new OrderException("用户不能为空");
        }

        if(confirmOrderReq.getAddress() == null){
            throw new OrderException("收货地址不能为空");
        }

        if(confirmOrderReq.getConsignee() == null){
            throw new OrderException("收货人信息不能为空");
        }

        if(confirmOrderReq.getGoodsId() == null){
            throw new OrderException("商品信息不能为空");
        }

        if(confirmOrderReq.getGoodsNumber() == null || confirmOrderReq.getGoodsNumber() <1){
            throw new OrderException("购买数量部不能为空");
        }
        if (queryGoodsRes ==null || !queryGoodsRes.getRestCode().equals(TradeEnums.RestEnum.SUCCESS.getCode())) {
            throw new OrderException("商品不存在");
        }
        if(queryGoodsRes.getGoodsNumber() < confirmOrderReq.getGoodsNumber()){
            throw new OrderException("商品库存不足");
        }
        if(queryGoodsRes.getGoodsPrice().compareTo(confirmOrderReq.getGoodsPrice())!=0){
            throw new OrderException("商品价格有变化,请重现下单");
        }
        if(confirmOrderReq.getShippingFee()==null){
            confirmOrderReq.setShippingFee(BigDecimal.ZERO);
        }
        if(confirmOrderReq.getOrderAmount() ==null){
            confirmOrderReq.setOrderAmount(BigDecimal.ZERO);
        }
    }
}
