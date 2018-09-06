package com.lxl.trade.mapper;

import com.lxl.trade.entity.Pay;
import com.lxl.trade.entity.PayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayMapper {
    int countByExample(PayExample example);

    int deleteByExample(PayExample example);

    int deleteByPrimaryKey(String payId);

    int insert(Pay record);

    int insertSelective(Pay record);

    List<Pay> selectByExample(PayExample example);

    Pay selectByPrimaryKey(String payId);

    int updateByExampleSelective(@Param("record") Pay record, @Param("example") PayExample example);

    int updateByExample(@Param("record") Pay record, @Param("example") PayExample example);

    int updateByPrimaryKeySelective(Pay record);

    int updateByPrimaryKey(Pay record);
}