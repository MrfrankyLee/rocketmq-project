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
}
