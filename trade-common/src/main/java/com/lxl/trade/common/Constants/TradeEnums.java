package com.lxl.trade.common.Constants;



/**
 * @author lixiaole
 * @date 2018/9/8
 * @Description
 */
public class TradeEnums {
    public enum  RestEnum{
        SUCCESS("1","成功"),FAIL("-1","失败");
        private String code;
        private String desc;

        RestEnum(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    public enum  ISEnum{
        YES("1","是"),NO("-1","否");
        private String code;
        private String desc;

        ISEnum(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    public enum OrderStatusEnum{
        NO_CONFIRM("0","未确认"),CONFIRM("1","已确认"),CANCEL("2","已取消"),INVALID("3","失效"),RETURNED("4","已退货");

        private String statusCode;
        private String desc;

        OrderStatusEnum(String statusCode, String desc) {
            this.statusCode = statusCode;
            this.desc = desc;
        }

        public String getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(String statusCode) {
            this.statusCode = statusCode;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public enum RestServerEnum{
        USER("localhost","user",8082),
        GOODS("localhost","goods",8083),
        COUPON("localhost","coupon",8084),
        ORDER("localhost","order",8085),
        PAY("localhost","pay",8086);


        private int serverPort;
        private String serverHost;
        private String contextPath;

        RestServerEnum(String serverHost,String contextPath, int serverPort) {
            this.serverPort = serverPort;
            this.serverHost = serverHost;
            this.contextPath = contextPath;
        }

        public String getServerUrl(){
            return "http://"+this.serverHost+":"+this.getServerPort()+"/"+this.getContextPath()+"/";
        }

        public int getServerPort() {
            return serverPort;
        }

        public String getServerHost() {
            return serverHost;
        }

        public String getContextPath() {
            return contextPath;
        }
    }

    public enum PayEnum{
        NO_PAY("0","未付款"),PAYING("1","支付中"),PAID("2","已付款");

        private String statusCode;

        private String desc;

        PayEnum(String statusCode, String desc) {
            this.statusCode = statusCode;
            this.desc = desc;
        }

        public String getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(String statusCode) {
            this.statusCode = statusCode;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public enum ShipEnum{
        NO_SHIP("0","未发货"),SHIPPED("1","已发货"),RECEIVED("2","已收货");

        private String statusCode;
        private String desc;

        ShipEnum(String statusCode, String desc) {
            this.statusCode = statusCode;
            this.desc = desc;
        }

        public String getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(String statusCode) {
            this.statusCode = statusCode;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public enum UserMoneyLogTypeEnum{
        PAID("1","订单付款"),RETURN("2","订单退款");

        private String statusCode;
        private String desc;

        UserMoneyLogTypeEnum(String statusCode, String desc) {
            this.statusCode = statusCode;
            this.desc = desc;
        }

        public String getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(String statusCode) {
            this.statusCode = statusCode;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
