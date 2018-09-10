package com.lxl.trade.common.exception;

/**
 * @author lixiaole
 * @date 2018/9/10
 * @Description
 */
public class OrderException extends RuntimeException {
    public OrderException() {
    }

    public OrderException(String message) {
        super(message);
    }

    public OrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
