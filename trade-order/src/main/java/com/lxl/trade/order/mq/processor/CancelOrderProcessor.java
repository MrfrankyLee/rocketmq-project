package com.lxl.trade.order.mq.processor;

import com.alibaba.fastjson.JSON;
import com.lxl.trade.common.Constants.TradeEnums;
import com.lxl.trade.common.protocol.mq.CancelOrderMQ;
import com.lxl.trade.common.rocketmq.MessageProcessor;
import com.lxl.trade.entity.Order;
import com.lxl.trade.mapper.OrderMapper;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;

/**
 * @author lixiaole
 * @date 2018/10/9
 * @Description 订单取消mq处理逻辑
 */
public class CancelOrderProcessor implements MessageProcessor {

    public static final Logger logger = LoggerFactory.getLogger(CancelOrderProcessor.class);
    @Autowired
    private OrderMapper orderMapper;

    public boolean handlerMeassage(MessageExt messageExt) {
        try {
            String body = new String(messageExt.getBody(),"UTF-8");//消息内容
            String msgId = messageExt.getMsgId();
            String tags = messageExt.getTags();
            String keys = messageExt.getKeys();
            logger.info("User CancelOrderProcessor receive message"+messageExt);
            CancelOrderMQ mq = JSON.parseObject(body,CancelOrderMQ.class);
            Order order = new Order();
            order.setOrderId(mq.getOrderId());
            order.setOrderStatus(TradeEnums.OrderStatusEnum.CANCEL.getStatusCode());
            orderMapper.updateByPrimaryKeySelective(order);
            return true;
        } catch (UnsupportedEncodingException e) {
           return false;
        }
    }
}
