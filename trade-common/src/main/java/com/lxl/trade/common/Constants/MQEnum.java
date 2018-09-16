package com.lxl.trade.common.Constants;

/**
 * @author lixiaole
 * @date 2018/9/13
 * @Description
 */
public class MQEnum {
    public enum TopicEnum{
        // 订单确认   订单取消   订单支付
        ORDER_CONFIRM("orderTopic","confirm"),ORDER_CANCEL("orderTopic","cancel"),PAY_PAID("payTopic","paid");
        private String topic;
        private String tags;

        TopicEnum(String topic, String tags) {
            this.topic = topic;
            this.tags = tags;
        }

        public String getTopic() {
            return topic;
        }

        public String getTags() {
            return tags;
        }
    }
}
