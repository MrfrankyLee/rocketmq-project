package com.lxl.trade.user.api;

import com.lxl.trade.common.api.UserApi;
import com.lxl.trade.common.protocol.user.QueryUserReq;
import com.lxl.trade.common.protocol.user.QueryUserRes;
import com.lxl.trade.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lixiaole
 * @date 2018/9/8
 * @Description
 */
@Controller
public class UserApiImpl implements UserApi {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/queryUserById",method = RequestMethod.POST)
    @ResponseBody
    public QueryUserRes queryUserById(@RequestBody QueryUserReq queryUserReq) {
        return userService.queryUserById(queryUserReq);
    }
}
