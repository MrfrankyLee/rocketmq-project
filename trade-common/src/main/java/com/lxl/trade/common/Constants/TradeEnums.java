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
}
