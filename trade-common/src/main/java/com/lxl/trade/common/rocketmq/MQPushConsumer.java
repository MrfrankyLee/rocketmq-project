package com.lxl.trade.common.rocketmq;

import com.lxl.trade.common.exception.MQException;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lixiaole
 * @date 2018/9/4
 * @Description
 */
public class MQPushConsumer {
    //创建日志记录对象
    public static final  Logger logger = LoggerFactory.getLogger(MQPushConsumer.class);
    //消费者对象
    private DefaultMQPushConsumer consumer;
    // 消费者组名称
    private String consumerGroupName;
    // 订阅的主题
    private String topic;
    //订阅的tags  默认订阅该主题下的所有消息
    private String tags ="*";
    //集群地址
    private String namesrvAddr;
    //最大线程数
    private int consumeThreadMax = 64;
    //最小线程数
    private int consumeThreadMin = 20;
    // 注入消息处理类
    private MessageProcessor processor;

    public void init() throws MQException {
        if(StringUtils.isBlank(consumerGroupName) ||StringUtils.isBlank(topic) ||StringUtils.isBlank(namesrvAddr)){
            throw new MQException("请不要传入空参");
        }
        consumer = new DefaultMQPushConsumer(consumerGroupName);
        consumer.setNamesrvAddr(namesrvAddr);
        try {
            // 订阅主题
            consumer.subscribe(topic,tags);
            // 设置消费从末尾消费
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
            // 设置最大线程数
            consumer.setConsumeThreadMax(consumeThreadMax);
            // 设置最小线程数
            consumer.setConsumeThreadMin(consumeThreadMin);
            MQMessageListener listener = new MQMessageListener();
            // 业务只需要写消息处理逻辑既可
            listener.setProcessor(processor);
            consumer.registerMessageListener(listener);
            consumer.start();
            logger.info("consumer is start...groupName:{} , topic:{},namesrvAddr:{}",consumerGroupName,topic,namesrvAddr);
        } catch (Exception e) {
            logger.info("consumer is fail...groupName:{} , topic:{},namesrvAddr:{}",consumerGroupName,topic,namesrvAddr,e);
            throw new MQException(e);
        }
    }

    public void setConsumerGroupName(String consumerGroupName) {
        this.consumerGroupName = consumerGroupName;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public void setConsumeThreadMax(int consumeThreadMax) {
        this.consumeThreadMax = consumeThreadMax;
    }

    public void setConsumeThreadMin(int consumeThreadMin) {
        this.consumeThreadMin = consumeThreadMin;
    }

    public void setProcessor(MessageProcessor processor) {
        this.processor = processor;
    }
}
