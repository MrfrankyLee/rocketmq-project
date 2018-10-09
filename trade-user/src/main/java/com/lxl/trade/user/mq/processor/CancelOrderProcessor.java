package com.lxl.trade.user.mq.processor;

import com.alibaba.fastjson.JSON;
import com.lxl.trade.common.Constants.TradeEnums;
import com.lxl.trade.common.protocol.mq.CancelOrderMQ;
import com.lxl.trade.common.protocol.user.ChangerUserMoneyReq;
import com.lxl.trade.common.rocketmq.MessageProcessor;
import com.lxl.trade.user.service.UserService;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

/**
 * @author lixiaole
 * @date 2018/10/9
 * @Description 订阅取消订单的消息
 */
public class CancelOrderProcessor implements MessageProcessor {

    public static final Logger logger = LoggerFactory.getLogger(CancelOrderProcessor.class);
    @Autowired
    private UserService userService;

    public boolean handlerMeassage(MessageExt messageExt) {
        try {
            String body = new String(messageExt.getBody(),"UTF-8");//消息内容
            String msgId = messageExt.getMsgId();
            String tags = messageExt.getTags();
            String keys = messageExt.getKeys();
            logger.info("User CancelOrderProcessor receive message"+body);
            CancelOrderMQ mq = JSON.parseObject(body,CancelOrderMQ.class);
            if(mq.getUserMoney() != null  && mq.getUserMoney().compareTo(BigDecimal.ZERO) ==1){
                ChangerUserMoneyReq changerUserMoneyReq = new ChangerUserMoneyReq();
                changerUserMoneyReq.setUserId(mq.getUserId());
                changerUserMoneyReq.setMoneyLogType(TradeEnums.UserMoneyLogTypeEnum.RETURN.getStatusCode());
                changerUserMoneyReq.setOrderId(mq.getOrderId());
                changerUserMoneyReq.setUserMoney(mq.getUserMoney());
                userService.changerUserMoney(changerUserMoneyReq);
            }
            return true;
        } catch (UnsupportedEncodingException e) {
           return false;
        }
    }
}
