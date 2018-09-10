package com.lxl.trade.common.api;

import com.lxl.trade.common.protocol.user.ChangerUserMoneyReq;
import com.lxl.trade.common.protocol.user.ChangerUserMoneyRes;
import com.lxl.trade.common.protocol.user.QueryUserReq;
import com.lxl.trade.common.protocol.user.QueryUserRes;

/**
 * @author lixiaole
 * @date 2018/9/8
 * @Description
 */
public interface UserApi {
    public QueryUserRes queryUserById(QueryUserReq queryUserReq);
    public ChangerUserMoneyRes changerUserMoney(ChangerUserMoneyReq ChangerUserMoneyReq);
}
