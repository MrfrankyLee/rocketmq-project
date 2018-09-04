package com.lxl.trade.common.exception;

/**
 * @author lixiaole
 * @date 2018/9/1
 * @Description
 */
public class MQException extends Exception{

    private static final long serialVersionUID = 2007915900989107051L;

    public MQException() {
        super();
    }

    public MQException(String message) {
        super(message);
    }

    public MQException(String message, Throwable cause) {
        super(message, cause);
    }

    public MQException(Throwable cause) {
        super(cause);
    }
}
