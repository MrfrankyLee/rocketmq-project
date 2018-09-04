package com.lxl.test;

import com.lxl.trade.common.exception.MQException;
import com.lxl.trade.common.rocketmq.MQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author lixiaole
 * @date 2018/9/4
 * @Description
 */
@RunWith(SpringJUnit4ClassRunner.class)
//引入spring配置文件
@ContextConfiguration(locations = "classpath:xml/spring-rocketmq-producer.xml")
public class ProducerTest {
    @Autowired
    private MQProducer mqProducer;

    @Test
    public void testMQproducer() throws MQException{

           SendResult sendResult = mqProducer.senMessage("testTpoic","order","12345678","this is a order messae");
           System.out.println(sendResult);

    }
}
