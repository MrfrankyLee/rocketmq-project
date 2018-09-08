package com.lxl.trade.user.service;

import com.lxl.trade.common.protocol.user.QueryUserReq;
import com.lxl.trade.common.protocol.user.QueryUserRes;

/**
 * @author lixiaole
 * @date 2018/9/8
 * @Description
 */
public interface UserService {
    public QueryUserRes queryUserById(QueryUserReq queryUserReq);
}
