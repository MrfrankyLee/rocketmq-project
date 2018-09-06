package com.lxl.trade.mapper;

import com.lxl.trade.entity.Coupon;
import com.lxl.trade.entity.CouponExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CouponMapper {
    int countByExample(CouponExample example);

    int deleteByExample(CouponExample example);

    int deleteByPrimaryKey(String couponId);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    List<Coupon> selectByExample(CouponExample example);

    Coupon selectByPrimaryKey(String couponId);

    int updateByExampleSelective(@Param("record") Coupon record, @Param("example") CouponExample example);

    int updateByExample(@Param("record") Coupon record, @Param("example") CouponExample example);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);
}