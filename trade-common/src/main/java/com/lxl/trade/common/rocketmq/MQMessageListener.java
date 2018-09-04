package com.lxl.trade.common.rocketmq;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author lixiaole
 * @date 2018/9/5
 * @Description
 */
// 消息监听器
public class MQMessageListener implements MessageListenerConcurrently {
    private static Logger logger = LoggerFactory.getLogger(MQMessageListener.class);
    private MessageProcessor processor;

    public void setProcessor(MessageProcessor processor) {
        this.processor = processor;
    }

    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        for(MessageExt message :list){
            boolean result = processor.handlerMeassage(message);
            if(!result){
                logger.error("消息接收失败,正在重试");
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
