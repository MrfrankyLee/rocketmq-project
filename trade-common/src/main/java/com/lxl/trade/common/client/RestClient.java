package com.lxl.trade.common.client;

import com.lxl.trade.common.protocol.user.QueryUserReq;
import com.lxl.trade.common.protocol.user.QueryUserRes;
import org.springframework.web.client.RestTemplate;

/**
 * @author lixiaole
 * @date 2018/9/9
 * @Description
 */
public class RestClient {
    private static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        QueryUserReq queryUserReq = new QueryUserReq();
        queryUserReq.setUserId(1);
        QueryUserRes queryUserRes = restTemplate.postForObject("http://localhost:8082/user/queryUserById", queryUserReq,QueryUserRes.class);
        System.out.println(queryUserRes);
    }
}
