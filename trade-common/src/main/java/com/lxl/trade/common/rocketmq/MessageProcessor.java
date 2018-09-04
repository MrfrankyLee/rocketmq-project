package com.lxl.trade.common.rocketmq;

import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author lixiaole
 * @date 2018/9/4
 * @Description
 */
public interface MessageProcessor {
    //处理消息接口
    boolean handlerMeassage(MessageExt messageExt);
}
