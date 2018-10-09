package com.lxl.trade.user.service.impl;

import com.lxl.trade.common.Constants.TradeEnums;
import com.lxl.trade.common.protocol.user.ChangerUserMoneyReq;
import com.lxl.trade.common.protocol.user.ChangerUserMoneyRes;
import com.lxl.trade.common.protocol.user.QueryUserReq;
import com.lxl.trade.common.protocol.user.QueryUserRes;
import com.lxl.trade.entity.User;
import com.lxl.trade.entity.UserMoneyLog;
import com.lxl.trade.entity.UserMoneyLogExample;
import com.lxl.trade.mapper.UserMapper;
import com.lxl.trade.mapper.UserMoneyLogMapper;
import com.lxl.trade.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lixiaole
 * @date 2018/9/8
 * @Description
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserMoneyLogMapper userMoneyLogMapper;

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

    @Transactional
    public ChangerUserMoneyRes changerUserMoney(ChangerUserMoneyReq changerUserMoneyReq) {
        ChangerUserMoneyRes changerUserMoneyRes = new ChangerUserMoneyRes();
        changerUserMoneyRes.setRestCode(TradeEnums.RestEnum.SUCCESS.getCode());
        changerUserMoneyRes.setRestDesc(TradeEnums.RestEnum.SUCCESS.getDesc());
        if(changerUserMoneyReq == null || changerUserMoneyReq.getUserId() ==null || changerUserMoneyReq.getUserMoney() == null){
            throw new RuntimeException("请求参数不正确");
        }
        if(changerUserMoneyReq.getUserMoney().compareTo(BigDecimal.ZERO)<0){
            throw new RuntimeException("金额不能小于0");
        }
        UserMoneyLog userMoneyLog = new UserMoneyLog();
        userMoneyLog.setOrderId(changerUserMoneyReq.getOrderId());
        userMoneyLog.setUserId(changerUserMoneyReq.getUserId());
        userMoneyLog.setUserMoney(changerUserMoneyReq.getUserMoney());
        userMoneyLog.setCreateTime(new Date());
        userMoneyLog.setMoneyLogType(changerUserMoneyReq.getMoneyLogType());

        User user =  new User();
        user.setUserId(changerUserMoneyReq.getUserId());
        user.setUserMoney(changerUserMoneyReq.getUserMoney());

        //查询付款记录
        UserMoneyLogExample logExample = new UserMoneyLogExample();
        logExample.createCriteria()
                .andOrderIdEqualTo(changerUserMoneyReq.getOrderId())
                .andUserIdEqualTo(changerUserMoneyReq.getUserId())
                .andMoneyLogTypeEqualTo(TradeEnums.UserMoneyLogTypeEnum.PAID.getStatusCode());
        int count = userMoneyLogMapper.countByExample(logExample);
        //订单付款
        if(changerUserMoneyReq.getMoneyLogType().equals(TradeEnums.UserMoneyLogTypeEnum.PAID.getStatusCode())){
            if(count > 0){
                throw new RuntimeException("已经付过款,不能重复付款");
            }
            userMapper.reduceUserMoney(user);
        }

        //订单退款
        if(changerUserMoneyReq.getMoneyLogType().equals(TradeEnums.UserMoneyLogTypeEnum.RETURN.getStatusCode())){

            if(count == 0){
                throw new  RuntimeException("没有付款信息,不能退款");
            }
            //防止多次退款
            logExample.createCriteria()
                    .andUserIdEqualTo(changerUserMoneyReq.getUserId())
                    .andOrderIdEqualTo(changerUserMoneyReq.getOrderId())
                    .andMoneyLogTypeEqualTo(TradeEnums.UserMoneyLogTypeEnum.RETURN.getStatusCode());
            count = userMoneyLogMapper.countByExample(logExample);
            if(count > 0){
                throw new RuntimeException("已经退过款了,不能重复退款");
            }
            userMapper.addUserMoney(user);
        }
        userMoneyLogMapper.insert(userMoneyLog);
        return changerUserMoneyRes;
    }
}
