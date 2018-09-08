package com.lxl.trade.user.service.impl;

import com.lxl.trade.common.Constants.TradeEnums;
import com.lxl.trade.common.protocol.user.QueryUserReq;
import com.lxl.trade.common.protocol.user.QueryUserRes;
import com.lxl.trade.entity.User;
import com.lxl.trade.mapper.UserMapper;
import com.lxl.trade.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lixiaole
 * @date 2018/9/8
 * @Description
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public QueryUserRes queryUserById(QueryUserReq queryUserReq) {
        QueryUserRes queryUserRes = new QueryUserRes();
        queryUserRes.setRestCode(TradeEnums.RestEnum.SUCCESS.getCode());
        queryUserRes.setRestDesc(TradeEnums.RestEnum.SUCCESS.getDesc());
        try{
            if(queryUserReq ==null || queryUserReq.getUserId() ==null){
                throw new RuntimeException("请求参数不正确");
            }
            User user  = userMapper.selectByPrimaryKey(queryUserReq.getUserId());
            if(user != null){
                BeanUtils.copyProperties(user,queryUserRes);
            }else {
                throw new RuntimeException("未查询到该用户");
            }
        }catch (Exception e) {
            queryUserRes.setRestCode(TradeEnums.RestEnum.FAIL.getCode());
            queryUserRes.setRestDesc(TradeEnums.RestEnum.FAIL.getDesc());
        }
        return queryUserRes;
    }
}
