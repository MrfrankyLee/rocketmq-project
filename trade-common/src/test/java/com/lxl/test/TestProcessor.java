package com.lxl.test;

import com.lxl.trade.common.rocketmq.MessageProcessor;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author lixiaole
 * @date 2018/9/5
 * @Description
 */
public class TestProcessor implements MessageProcessor {
    public boolean handlerMeassage(MessageExt messageExt) {
        System.out.println("收到消息:"+messageExt.toString());
        return true;
    }
}
