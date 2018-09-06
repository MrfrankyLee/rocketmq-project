package com.lxl.trade.mapper;

import com.lxl.trade.entity.UserMoneyLog;
import com.lxl.trade.entity.UserMoneyLogExample;
import com.lxl.trade.entity.UserMoneyLogKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMoneyLogMapper {
    int countByExample(UserMoneyLogExample example);

    int deleteByExample(UserMoneyLogExample example);

    int deleteByPrimaryKey(UserMoneyLogKey key);

    int insert(UserMoneyLog record);

    int insertSelective(UserMoneyLog record);

    List<UserMoneyLog> selectByExample(UserMoneyLogExample example);

    UserMoneyLog selectByPrimaryKey(UserMoneyLogKey key);

    int updateByExampleSelective(@Param("record") UserMoneyLog record, @Param("example") UserMoneyLogExample example);

    int updateByExample(@Param("record") UserMoneyLog record, @Param("example") UserMoneyLogExample example);

    int updateByPrimaryKeySelective(UserMoneyLog record);

    int updateByPrimaryKey(UserMoneyLog record);
}