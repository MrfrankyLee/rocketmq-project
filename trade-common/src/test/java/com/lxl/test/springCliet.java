package com.lxl.test;

import com.lxl.trade.common.api.UserApi;
import com.lxl.trade.common.protocol.user.QueryUserReq;
import com.lxl.trade.common.protocol.user.QueryUserRes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author lixiaole
 * @date 2018/9/9
 * @Description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:xml/spring-restclientproxy.xml")
public class springCliet {
    @Autowired
    private UserApi userApi;

    @Test
    public void testMethod(){
        QueryUserReq queryUserReq = new QueryUserReq();
        queryUserReq.setUserId(2);
        QueryUserRes  queryUserRes = userApi.queryUserById(queryUserReq);
        System.out.println(queryUserRes);
    }
}
