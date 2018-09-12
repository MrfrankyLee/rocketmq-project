package com.lxl.trade.order.service.impl;

import com.lxl.trade.common.Constants.TradeEnums;
import com.lxl.trade.common.api.CouponApi;
import com.lxl.trade.common.api.GoodsApi;
import com.lxl.trade.common.api.UserApi;
import com.lxl.trade.common.exception.OrderException;
import com.lxl.trade.common.protocol.coupon.ChangeCouponStatusReq;
import com.lxl.trade.common.protocol.coupon.ChangeCouponStatusRes;
import com.lxl.trade.common.protocol.coupon.QueryCouponReq;
import com.lxl.trade.common.protocol.coupon.QueryCouponRes;
import com.lxl.trade.common.protocol.goods.QueryGoodsReq;
import com.lxl.trade.common.protocol.goods.QueryGoodsRes;
import com.lxl.trade.common.protocol.goods.ReduceGoodsNumberReq;
import com.lxl.trade.common.protocol.goods.ReduceGoodsNumberRes;
import com.lxl.trade.common.protocol.order.ConfirmOrderReq;
import com.lxl.trade.common.protocol.order.ConfirmOrderRes;
import com.lxl.trade.common.protocol.user.ChangerUserMoneyReq;
import com.lxl.trade.common.protocol.user.ChangerUserMoneyRes;
import com.lxl.trade.common.protocol.user.QueryUserReq;
import com.lxl.trade.common.protocol.user.QueryUserRes;
import com.lxl.trade.common.rocketmq.MQProducer;
import com.lxl.trade.common.util.IDgenerator;
import com.lxl.trade.entity.Order;
import com.lxl.trade.mapper.OrderMapper;
import com.lxl.trade.order.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

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
    private CouponApi couponApi;

    @Autowired
    private UserApi userApi;
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private MQProducer mqProducer;

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
            String orderId = saveNoConfirmOrder(confirmOrderReq);
            // 3调用远程服务,扣优惠券 扣库存 扣余额  如果调用成功->更改状态可见 失败->发送mq消息,取消订单

        } catch (Exception e) {
            confirmOrderRes.setRestCode(TradeEnums.RestEnum.FAIL.getCode());
            confirmOrderRes.setRestDesc(e.getMessage());
        }

        return confirmOrderRes;
    }

    // 3调用远程服务,扣优惠券 扣库存 扣余额  如果调用成功->更改状态可见 失败->发送mq消息,取消订单
    private void callRemoteService(String orderId ,ConfirmOrderReq confirmOrderReq){
        try{
            //调用优惠券服务
            if(StringUtils.isNotBlank(confirmOrderReq.getCouponId())) {
                ChangeCouponStatusReq changeCouponStatusReq = new ChangeCouponStatusReq();
                changeCouponStatusReq.setCouponId(confirmOrderReq.getCouponId());
                changeCouponStatusReq.setOrderId(orderId);
                changeCouponStatusReq.setUserId(confirmOrderReq.getUserId());
                ChangeCouponStatusRes changeCouponStatusRes = couponApi.changeCouponStatus(changeCouponStatusReq);
                if (!changeCouponStatusRes.getRestCode().equals(TradeEnums.RestEnum.SUCCESS.getCode())) {
                    throw new Exception("优惠券使用失败");
                }
            }
             //扣余额
             if(confirmOrderReq.getMoneyPaid()!= null && confirmOrderReq.getMoneyPaid().compareTo(BigDecimal.ZERO)==1){
                   ChangerUserMoneyReq changerUserMoneyReq =  new ChangerUserMoneyReq();
                   changerUserMoneyReq.setOrderId(orderId);
                   changerUserMoneyReq.setUserId(confirmOrderReq.getUserId());
                   changerUserMoneyReq.setMoneyLogType(TradeEnums.UserMoneyLogTypeEnum.PAID.getStatusCode());
                   ChangerUserMoneyRes changerUserMoneyRes = userApi.changerUserMoney(changerUserMoneyReq);
                   if(!changerUserMoneyRes.getRestCode().equals(TradeEnums.RestEnum.SUCCESS.getCode())){
                       throw new Exception("扣除用户余额失败");
                   }
             }

             // 扣库存
            ReduceGoodsNumberReq reduceGoodsNumberReq = new ReduceGoodsNumberReq();
            reduceGoodsNumberReq.setGoodsId(confirmOrderReq.getGoodsId());
            reduceGoodsNumberReq.setGoodsNumber(confirmOrderReq.getGoodsNumber());
            reduceGoodsNumberReq.setOrderId(orderId);
            ReduceGoodsNumberRes reduceGoodsNumberRes = goodsApi.reduceGoodsNumber(reduceGoodsNumberReq);
            if(!reduceGoodsNumberRes.getRestCode().equals(TradeEnums.RestEnum.SUCCESS.getCode())){
                throw new Exception("库存扣除失败");
            }
            // 更改订单状态
            Order order = new Order();
            order.setOrderId(orderId);
            order.setOrderStatus(TradeEnums.OrderStatusEnum.CONFIRM.getStatusCode());
            order.setConfirmTime(new Date());
            int i = orderMapper.updateByPrimaryKey(order);
            if(i <=0)
            {
                throw new Exception("修改订单状态失败");
            }

        }catch (Exception e){
            // 失败  发送MQ
            // mqProducer.senMessage();
        }
    }
    private String saveNoConfirmOrder(ConfirmOrderReq confirmOrderReq) throws Exception {
        Order order = new Order();
        String orderId = IDgenerator.generatorUUID();
        order.setOrderId(orderId);
        order.setAddress(confirmOrderReq.getAddress());
        order.setUserId(confirmOrderReq.getUserId());
        order.setOrderStatus(TradeEnums.OrderStatusEnum.NO_CONFIRM.getStatusCode());
        order.setPayStatus(TradeEnums.PayEnum.NO_PAY.getStatusCode());
        order.setShippingStatus(TradeEnums.ShipEnum.NO_SHIP.getStatusCode());
        order.setAddress(confirmOrderReq.getAddress());
        order.setConsignee(confirmOrderReq.getConsignee());
        order.setGoodsId(confirmOrderReq.getGoodsId());
        order.setGoodsNumber(confirmOrderReq.getGoodsNumber());
        order.setGoodsPrice(confirmOrderReq.getGoodsPrice());
        //物品总价
        BigDecimal goodsAmount = confirmOrderReq.getGoodsPrice().multiply(new BigDecimal(confirmOrderReq.getGoodsNumber()));
        order.setGoodsAmount(goodsAmount);
        BigDecimal shippFee = calculateShippFee(goodsAmount);
        if(shippFee.compareTo(confirmOrderReq.getShippingFee())!= 0){
                throw new Exception("快递费用不正确");
        }
        order.setShippingFee(shippFee);

        BigDecimal orderTotalAmount = goodsAmount.add(shippFee);
        if(orderTotalAmount.compareTo(confirmOrderReq.getOrderAmount())!=0){
            throw new Exception("订单总价不正确,请重新下单");
        }
        order.setOrderAmount(orderTotalAmount);
        String couponId = confirmOrderReq.getCouponId();
        //优惠券支付
        if(StringUtils.isNotBlank(couponId)){
            QueryCouponReq queryCouponReq = new QueryCouponReq();
            queryCouponReq.setCouponId(couponId);
            QueryCouponRes queryCouponRes = couponApi.queryCoupon(queryCouponReq);
            if(queryCouponReq == null || !queryCouponRes.getRestCode().equals(TradeEnums.RestEnum.SUCCESS.getCode())){
                throw new Exception("使用非法优惠券");
            }
            if(queryCouponRes.getIsUsed().equals(TradeEnums.ISEnum.YES.getCode())){
                throw new Exception("优惠券已经使用");
            }
            order.setCouponId(couponId);
            order.setCouponPaid(queryCouponRes.getCouponPrice());
        }else {
            order.setCouponPaid(BigDecimal.ZERO);
        }
        // 余额支付
        if(confirmOrderReq.getMoneyPaid()!=null){
            int r = confirmOrderReq.getMoneyPaid().compareTo(BigDecimal.ZERO);
            if(r < 0){
                throw new Exception("余额金额非法");
            }
            if(r == 1){
                //判断金额是否足够
                QueryUserReq queryUserReq = new QueryUserReq();
                queryUserReq.setUserId(confirmOrderReq.getUserId());
                QueryUserRes queryUserRes = userApi.queryUserById(queryUserReq);
                if(queryUserRes == null || !queryUserRes.getRestCode().equals(TradeEnums.RestEnum.SUCCESS.getCode())){
                    throw new Exception("用户非法");
                }

                if(queryUserRes.getUserMoney().compareTo(confirmOrderReq.getMoneyPaid()) ==-1){
                    throw new Exception("余额不足");
                }
                order.setMoneyPaid(confirmOrderReq.getMoneyPaid());
            }

        }else {
            order.setMoneyPaid(BigDecimal.ZERO);
        }

        BigDecimal surplusPayAmout = orderTotalAmount.subtract(order.getMoneyPaid()).subtract(order.getCouponPaid());
        order.setPayAmount(surplusPayAmout);
        order.setAddTime(new Date());
        int result = orderMapper.insert(order);
        if(result != 1){
            throw new Exception("保存不可见订单失败");
        }
        return orderId;
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

    private  BigDecimal calculateShippFee(BigDecimal goodAmount){
        if(goodAmount.doubleValue() >100.00){
            return BigDecimal.ZERO;
        }else {
            return new BigDecimal(10.00);
        }
    }
}
