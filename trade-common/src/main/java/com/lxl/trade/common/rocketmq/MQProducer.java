package com.lxl.trade.common.rocketmq;

import com.lxl.trade.common.exception.MQException;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lixiaole
 * @date 2018/9/1
 * @Description
 */
public class MQProducer {
    // 创建日志记录对象
    public static final Logger logger = LoggerFactory.getLogger(MQProducer.class);
    // producer对象
    private DefaultMQProducer producer;
    // 生产者群组名称
    private String groupName;
    // 集群地址
    private String nameSrvAddr;
    // 消息最大值 默认最大为4M
    private int maxMessageSize = 1024 * 1024 *4; //4M
    // 发送消息超时时间长度 默认为3000 即3秒
    private int sendMsgTimeout = 10000;

    public void setProducer(DefaultMQProducer producer) {
        this.producer = producer;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setNameSrvAddr(String nameSrvAddr) {
        this.nameSrvAddr = nameSrvAddr;
    }

    public void setMaxMessageSize(int maxMessageSize) {
        this.maxMessageSize = maxMessageSize;
    }

    public void setSendMsgTimeout(int sendMsgTimeout) {
        this.sendMsgTimeout = sendMsgTimeout;
    }

    // 初始化producer
    public void init() throws MQException {
        if(StringUtils.isBlank(groupName)){
            throw new MQException("groupName is blank");
        }
        if(StringUtils.isBlank(nameSrvAddr)){
            throw new MQException("nameSrvAddr  is blank");
        }
        this.producer = new DefaultMQProducer(this.groupName);
        this.producer.setNamesrvAddr(this.nameSrvAddr);
        this.producer.setMaxMessageSize(this.maxMessageSize);
        this.producer.setSendMsgTimeout(this.sendMsgTimeout);
        try {
            this.producer.start();
            logger.info(String.format("producer is  start!groupName:[%s],nameSrvAddr:[%s]",this.groupName,this.nameSrvAddr));
        } catch (MQClientException e) {
            logger.error(String.format("producer error!groupName:[%s],nameSrvAddr:[%s]",this.groupName,this.nameSrvAddr),e);
            throw new MQException(e);
        }
    }

    // 发送消息
    public SendResult senMessage(String topic , String tags ,String keys ,String messageBody) throws MQException {
        Message message = new Message(topic,tags,keys,messageBody.getBytes());
        if(StringUtils.isBlank(topic)){
            throw new MQException("Topic is blank");
        }
        if(StringUtils.isBlank(messageBody)){
            throw new MQException("messageBody is blank");
        }
        try {
           SendResult sendResult =  this.producer.send(message);
           return sendResult;
        } catch (Exception e) {
            logger.error("message send fail:{}",e.getMessage(),e);
            throw new MQException(e);
        }
    }
}
