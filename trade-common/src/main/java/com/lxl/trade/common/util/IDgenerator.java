package com.lxl.trade.common.util;

import java.util.UUID;

/**
 * @author lixiaole
 * @date 2018/9/10
 * @Description
 */
public class IDgenerator {
    public static String generatorUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
